package fdmc.repository;

import java.util.List;

public interface GenericRepository<E, K> {
    void save(E entity);

    E getById(String id);

    List<E> getAll();
}
