package com.salon.coiffure.service.base;

import java.util.List;
import java.util.Optional;

/**
 * Interface de base pour tous les services CRUD.
 * Définit les opérations communes pour éviter la duplication de code.
 *
 * @param <T> Type de l'entité
 * @param <ID> Type de l'identifiant
 */
public interface BaseService<T, ID> {

    /**
     * Crée une nouvelle entité.
     *
     * @param entity L'entité à créer
     * @return L'entité créée
     */
    T create(T entity);

    /**
     * Trouve une entité par son ID.
     *
     * @param id L'ID de l'entité
     * @return Un Optional contenant l'entité si elle est trouvée
     */
    Optional<T> findById(ID id);

    /**
     * Récupère toutes les entités.
     *
     * @return Une liste de toutes les entités
     */
    List<T> findAll();

    /**
     * Met à jour une entité existante.
     *
     * @param entity L'entité avec les informations mises à jour
     * @return L'entité mise à jour
     */
    T update(T entity);

    /**
     * Supprime une entité par son ID.
     *
     * @param id L'ID de l'entité à supprimer
     */
    void deleteById(ID id);
}
