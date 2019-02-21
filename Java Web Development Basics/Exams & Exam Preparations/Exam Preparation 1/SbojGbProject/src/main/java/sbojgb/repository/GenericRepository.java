package sbojgb.repository;

import java.util.List;

public interface GenericRepository<Entity, Key> {
    Entity save(Entity entity);

    Entity getById(Key id);

    List<Entity> getAll();
}
