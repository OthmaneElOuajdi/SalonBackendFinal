package com.salon.coiffure.service;

/**
 * Interface du service de validation.
 * Gère les validations métier (téléphone, email, etc.).
 */
public interface ValidationService {

    /**
     * Valide un numéro de téléphone belge.
     *
     * @param phoneNumber Le numéro de téléphone
     * @return true si valide, false sinon
     */
    boolean validatePhoneNumber(String phoneNumber);

    /**
     * Valide un numéro de mobile belge uniquement.
     *
     * @param phoneNumber Le numéro de téléphone
     * @return true si valide, false sinon
     */
    boolean validateMobileNumber(String phoneNumber);

    /**
     * Valide un email et vérifie qu'il n'est pas jetable.
     *
     * @param email L'adresse email
     * @return true si valide et non jetable, false sinon
     */
    boolean validateNonDisposableEmail(String email);

    /**
     * Formate un numéro de téléphone belge.
     *
     * @param phoneNumber Le numéro de téléphone
     * @return Le numéro formaté
     */
    String formatPhoneNumber(String phoneNumber);

    /**
     * Vérifie si un email utilise un domaine jetable.
     *
     * @param email L'adresse email
     * @return true si jetable, false sinon
     */
    boolean isDisposableEmailDomain(String email);

    /**
     * Valide un code postal belge.
     *
     * @param postalCode Le code postal
     * @return true si valide, false sinon
     */
    boolean validatePostalCode(String postalCode);
}
