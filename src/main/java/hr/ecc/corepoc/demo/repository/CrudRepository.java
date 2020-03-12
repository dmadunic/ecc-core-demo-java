package hr.ecc.corepoc.demo.repository;

import java.util.List;

/**
 * Interface describing base CRUD repository.
 *
 * @param <T>
 * @param <ID>
 */
public interface CrudRepository<T, ID> {

    T findById(ID id);
    List<T> findAll();
    T save(T object);
    void delete(T object);
    void deleteById(ID id);
    long count();
}
