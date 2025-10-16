package com.salon.coiffure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

/**
 * Configuration de la sécurité Spring pour l'application Coiffure.
 * 
 * Deux configurations sont chargées selon le profil actif :
 * - dev : sécurité allégée (tout est autorisé)
 * - prod : sécurité renforcée (authentification requise)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Encodeur de mot de passe utilisant BCrypt (force = 12).
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    /**
     * Configuration de sécurité pour le profil de développement.
     * - CSRF désactivé
     * - Toutes les requêtes autorisées
     * - Headers de sécurité de base appliqués
     */
    @Bean
    @Profile("dev")
    public SecurityFilterChain devSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            // Désactivation du CSRF pour faciliter les tests
            .csrf(csrf -> csrf.disable())
            
            // Sessions autorisées si nécessaire
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            )

            // Autoriser toutes les requêtes en mode dev
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            )

            // Headers de sécurité recommandés
            .headers(headers -> headers
                .frameOptions(frame -> frame.deny())
                .contentTypeOptions(withDefaults -> {})
                .httpStrictTransportSecurity(hsts -> hsts
                    .maxAgeInSeconds(31536000)
                    .includeSubDomains(true)
                )
                .referrerPolicy(referrer -> referrer.policy(
                    ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN))
            );

        return http.build();
    }

    /**
     * Configuration de sécurité pour le profil de production.
     * - CSRF activé avec Cookie CSRF Token
     * - Sessions limitées
     * - Accès public restreint à certains endpoints
     * - Headers de sécurité stricts (HSTS, ReferrerPolicy, etc.)
     */
    @Bean
    @Profile("prod")
    public SecurityFilterChain prodSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            // Activation du CSRF avec token stocké dans un cookie (HttpOnly désactivé)
            .csrf(csrf -> csrf
                .csrfTokenRepository(
                    org.springframework.security.web.csrf.CookieCsrfTokenRepository.withHttpOnlyFalse()
                )
            )

            // Gestion stricte des sessions
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
            )

            // Règles d’autorisation : certaines routes publiques, le reste protégé
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**", "/health", "/info").permitAll()
                .anyRequest().authenticated()
            )

            // Headers de sécurité
            .headers(headers -> headers
                .frameOptions(frame -> frame.deny())
                .contentTypeOptions(withDefaults -> {})
                .httpStrictTransportSecurity(hsts -> hsts
                    .maxAgeInSeconds(31536000)
                    .includeSubDomains(true)
                    .preload(true)
                )
                .referrerPolicy(referrer -> referrer.policy(
                    ReferrerPolicyHeaderWriter.ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN))
            );

        return http.build();
    }
}
