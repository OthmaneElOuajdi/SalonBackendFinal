package com.salon.coiffure.config;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import java.util.regex.Pattern;

/**
 * Logback converter that masks sensitive data from log messages to prevent
 * credential leaks, PII exposure, or infrastructure fingerprinting.
 *
 * <p>Usage (in logback-spring.xml):
 * <pre>{@code
 * <conversionRule conversionWord="maskedMsg"
 *                 converterClass="com.salon.coiffure.config.SensitiveDataMaskingConverter" />
 * <encoder>
 *     <pattern>%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %maskedMsg%n</pattern>
 * </encoder>
 * }</pre>
 *
 * This converter replaces critical information (passwords, tokens, versions,
 * usernames, ports, etc.) with redacted placeholders before writing logs.
 */
public class SensitiveDataMaskingConverter extends MessageConverter {

    // === CRITICAL LEVEL PATTERNS ===
    private static final Pattern GENERATED_PASSWORD_PATTERN = Pattern.compile(
        "(Using generated security password: )[a-f0-9-]+",
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
        "(password[\\s]*[:=][\\s]*)[^\\s,}]+",
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern SECURITY_FILTER_PATTERN = Pattern.compile(
        "(DefaultSecurityFilterChain[^\\]]*\\[)[^\\]]+",
        Pattern.CASE_INSENSITIVE
    );

    // === HIGH LEVEL: PII EXPOSURE ===
    private static final Pattern USERNAME_PATTERN = Pattern.compile(
        "(C:\\\\Users\\\\)[^\\\\]+",
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern PROJECT_PATH_PATTERN = Pattern.compile(
        "(C:\\\\Users\\\\[^\\\\]+\\\\Desktop\\\\)[^\\s]+",
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern STARTED_BY_PATTERN = Pattern.compile(
        "(started by )[^\\s]+",
        Pattern.CASE_INSENSITIVE
    );

    // === MEDIUM LEVEL: INFRASTRUCTURE INFO ===
    private static final Pattern PORT_PATTERN = Pattern.compile(
        "(port[\\s]*[:=]?[\\s]*)(\\d{4,5})",
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern LIVERELOAD_PORT_PATTERN = Pattern.compile(
        "(LiveReload server[^\\d]*)(\\d{4,5})",
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern DB_CONNECTION_PATTERN = Pattern.compile(
        "(PgConnection@)[a-f0-9]+",
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern DB_VERSION_PATTERN = Pattern.compile(
        "(Database version: )[\\d.]+",
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern HIKARI_POOL_PATTERN = Pattern.compile(
        "(HikariPool-)(\\d+)",
        Pattern.CASE_INSENSITIVE
    );

    private static final Pattern VERSION_PATTERN = Pattern.compile(
        "(Spring Boot|Tomcat|Java|Hibernate|Validator)[^\\d]*([\\d.]+[^\\s]*)",
        Pattern.CASE_INSENSITIVE
    );

    // === LOW LEVEL: PROCESS INFO ===
    private static final Pattern PID_PATTERN = Pattern.compile(
        "(PID[\\s]*)(\\d+)",
        Pattern.CASE_INSENSITIVE
    );

    @Override
    public String convert(ILoggingEvent event) {
        String message = super.convert(event);
        if (message == null || message.isBlank()) {
            return message;
        }

        // === CRITICAL: must always be masked first ===
        message = GENERATED_PASSWORD_PATTERN.matcher(message).replaceAll("$1[SECRET REDACTED]");
        message = PASSWORD_PATTERN.matcher(message).replaceAll("$1[SECRET REDACTED]");
        message = SECURITY_FILTER_PATTERN.matcher(message).replaceAll("$1[FILTERS REDACTED]");

        // === HIGH: personal info masking ===
        message = USERNAME_PATTERN.matcher(message).replaceAll("C:\\\\Users\\\\[USER]");
        message = PROJECT_PATH_PATTERN.matcher(message).replaceAll("$1[PATH]");
        message = STARTED_BY_PATTERN.matcher(message).replaceAll("$1[USER]");

        // === MEDIUM: infrastructure info ===
        message = PORT_PATTERN.matcher(message).replaceAll("$1[PORT]");
        message = LIVERELOAD_PORT_PATTERN.matcher(message).replaceAll("$1[PORT]");
        message = DB_CONNECTION_PATTERN.matcher(message).replaceAll("$1[DB_INFO]");
        message = DB_VERSION_PATTERN.matcher(message).replaceAll("$1[VERSION]");
        message = HIKARI_POOL_PATTERN.matcher(message).replaceAll("$1[POOL_ID]");
        message = VERSION_PATTERN.matcher(message).replaceAll("$1 [VERSION]");

        // === LOW: miscellaneous ===
        message = PID_PATTERN.matcher(message).replaceAll("$1[PID]");

        return message;
    }
}
