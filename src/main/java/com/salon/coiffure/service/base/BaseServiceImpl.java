package com.salon.coiffure.service.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implémentation de base pour tous les services CRUD.
 * Élimine la duplication de code en centralisant les opérations communes.
 *
 * @param <T> Type de l'entité
 * @param <ID> Type de l'identifiant
 */
public abstract class BaseServiceImpl<T, ID> implements BaseService<T, ID> {

    protected final JpaRepository<T, ID> repository;

    protected BaseServiceImpl(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T create(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public T update(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
