package repository;

import domain.entities.Product;

import java.util.List;

public interface GenericRepository<E, K> {
    E save(E entity);

    E findById(K id);

    List<E> findAll();

    Product findByName(String name);
}
