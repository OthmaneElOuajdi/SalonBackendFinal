package com.salon.coiffure.service.impl;

import java.util.Set;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.salon.coiffure.service.ValidationService;

/**
 * Implémentation du service de validation.
 * Contient la logique de validation pour les données belges.
 */
@Service
public class ValidationServiceImpl implements ValidationService {

    private static final Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);

    private static final Pattern BELGIAN_MOBILE_PATTERN = Pattern.compile(
        "^(\\+32\\s?|0)4[0-9]{2}\\s?[0-9]{2}\\s?[0-9]{2}\\s?[0-9]{2}$"
    );

    private static final Pattern BELGIAN_LANDLINE_PATTERN = Pattern.compile(
        "^(\\+32\\s?|0)[1-9][0-9]\\s?[0-9]{2}\\s?[0-9]{2}\\s?[0-9]{2}$"
    );

    private static final Pattern BELGIAN_POSTAL_CODE_PATTERN = Pattern.compile(
        "^[1-9][0-9]{3}$"
    );

    private static final Set<String> DISPOSABLE_EMAIL_DOMAINS = Set.of(
        "10minutemail.com", "guerrillamail.com", "mailinator.com", "tempmail.org",
        "yopmail.com", "temp-mail.org", "throwaway.email", "maildrop.cc",
        "sharklasers.com", "guerrillamail.info", "guerrillamail.biz", "guerrillamail.net",
        "guerrillamail.org", "guerrillamailblock.com", "pokemail.net", "spam4.me",
        "tempail.com", "tempmailaddress.com", "emailondeck.com", "jetable.org",
        "mytrashmail.com", "mailnesia.com", "trashmail.net", "dispostable.com",
        "tempinbox.com", "mohmal.com", "fakeinbox.com", "mailcatch.com",
        "mailnator.com", "tempmailer.com", "temp-mail.io", "disposablemail.com"
    );

    @Override
    public boolean validatePhoneNumber(String phoneNumber) {
        logger.debug("Validation du numéro de téléphone belge: {}", phoneNumber);
        boolean isValid = isValidBelgianPhoneNumber(phoneNumber);
        if (!isValid) {
            logger.warn("Numéro de téléphone belge invalide: {}", phoneNumber);
        } else {
            logger.debug("Numéro de téléphone belge valide: {}", phoneNumber);
        }
        return isValid;
    }

    @Override
    public boolean validateMobileNumber(String phoneNumber) {
        logger.debug("Validation du numéro de mobile belge: {}", phoneNumber);
        boolean isValid = isValidBelgianMobileNumber(phoneNumber);
        if (!isValid) {
            logger.warn("Numéro de mobile belge invalide: {}", phoneNumber);
        }
        return isValid;
    }

    @Override
    public boolean validateNonDisposableEmail(String email) {
        logger.debug("Validation de l'email non-jetable: {}", email);
        boolean isValid = isValidNonDisposableEmail(email);
        if (!isValid) {
            if (isDisposableEmailDomain(email)) {
                logger.warn("Email jetable détecté et rejeté: {}", email);
            } else {
                logger.warn("Format d'email invalide: {}", email);
            }
        } else {
            logger.debug("Email valide et non-jetable: {}", email);
        }
        return isValid;
    }

    @Override
    public String formatPhoneNumber(String phoneNumber) {
        logger.debug("Formatage du numéro de téléphone belge: {}", phoneNumber);
        if (!validatePhoneNumber(phoneNumber)) {
            logger.warn("Impossible de formater un numéro invalide: {}", phoneNumber);
            return phoneNumber;
        }

        String digits = phoneNumber.replaceAll("[^0-9+]", "");

        if (digits.startsWith("+32")) {
            digits = "0" + digits.substring(3);
        }

        if (digits.length() == 10) {
            String formatted = String.format("%s %s %s %s %s",
                digits.substring(0, 2), digits.substring(2, 4), digits.substring(4, 6),
                digits.substring(6, 8), digits.substring(8, 10));
            logger.debug("Numéro formaté de '{}' vers '{}'", phoneNumber, formatted);
            return formatted;
        }

        return phoneNumber;
    }

    @Override
    public boolean validatePostalCode(String postalCode) {
        logger.debug("Validation du code postal belge: {}", postalCode);
        boolean isValid = isValidBelgianPostalCode(postalCode);
        if (!isValid) {
            logger.warn("Code postal belge invalide: {} (doit être 4 chiffres, 1000-9999)", postalCode);
        } else {
            logger.debug("Code postal belge valide: {}", postalCode);
        }
        return isValid;
    }

    @Override
    public boolean isDisposableEmailDomain(String email) {
        logger.debug("Vérification si l'email utilise un domaine jetable: {}", email);
        if (email == null || email.trim().isEmpty()) {
            return false;
        }

        String domain = email.toLowerCase().trim();
        int atIndex = domain.lastIndexOf('@');
        if (atIndex == -1 || atIndex == domain.length() - 1) {
            return false;
        }

        domain = domain.substring(atIndex + 1);
        boolean isDisposable = DISPOSABLE_EMAIL_DOMAINS.contains(domain);
        
        if (isDisposable) {
            logger.warn("Domaine email jetable détecté: {}", email);
        }
        
        return isDisposable;
    }

    // --- Méthodes privées ---

    private boolean isValidBelgianPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        String cleanPhone = phoneNumber.replaceAll("\\s+", " ").trim();
        return BELGIAN_MOBILE_PATTERN.matcher(cleanPhone).matches()
            || BELGIAN_LANDLINE_PATTERN.matcher(cleanPhone).matches();
    }

    private boolean isValidBelgianMobileNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            return false;
        }
        String cleanPhone = phoneNumber.replaceAll("\\s+", " ").trim();
        return BELGIAN_MOBILE_PATTERN.matcher(cleanPhone).matches();
    }

    private boolean isValidNonDisposableEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            return false;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            return false;
        }
        return !isDisposableEmailDomain(email);
    }

    private boolean isValidBelgianPostalCode(String postalCode) {
        if (postalCode == null || postalCode.trim().isEmpty()) {
            return false;
        }
        return BELGIAN_POSTAL_CODE_PATTERN.matcher(postalCode.trim()).matches();
    }
}
