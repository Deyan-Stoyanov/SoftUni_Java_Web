package repository;

import java.util.List;

public interface GenericRepository<E, K> {
    void save (E entity);

    E findById(String id);

    List<E> findAll();
}
