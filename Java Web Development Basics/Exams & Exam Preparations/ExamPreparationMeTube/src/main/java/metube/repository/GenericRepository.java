package metube.repository;

import java.util.List;

public interface GenericRepository<Entity, Id> {
    Entity save(Entity entity);

    Entity update(Entity entity);

    Entity getById(Id id);

    List<Entity> getAll();
}
