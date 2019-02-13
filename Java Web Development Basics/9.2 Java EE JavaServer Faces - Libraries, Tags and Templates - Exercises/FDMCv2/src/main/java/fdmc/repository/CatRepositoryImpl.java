package fdmc.repository;

import fdmc.domain.entity.Cat;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class CatRepositoryImpl implements CatRepository {
    private final EntityManager entityManager;

    @Inject
    public CatRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(Cat entity) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(entity);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public Cat getById(String id) {
        return this.entityManager
                .createQuery("SELECT c FROM Cat c WHERE c.id=:id", Cat.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Cat> getAll() {
        return this.entityManager
                .createQuery("SELECT c FROM Cat c", Cat.class)
                .getResultList();
    }

    @Override
    public Cat getByName(String name) {
        return this.entityManager
                .createQuery("SELECT c FROM Cat c WHERE c.name=:name", Cat.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
