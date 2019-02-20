package panda.repository;

import java.util.List;

public interface GenericRepository<E, K> {
    E save(E e);

    E getById(String id);

    List<E> getAll();
}
