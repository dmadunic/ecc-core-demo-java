package hr.ecc.corepoc.demo.repository;

import java.util.Collection;

/**
 * Interface describing base CRUD repository.
 *
 * @param <T>
 * @param <ID>
 */
public interface CrudRepository<T, ID> {

    T findById(ID id);
    Collection<T> findAll();
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
    long count();
}
