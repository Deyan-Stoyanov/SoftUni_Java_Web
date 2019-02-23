package metube.repository;

import metube.domain.entity.Tube;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {
    private final EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Tube save(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();
        return tube;
    }

    @Override
    public Tube update(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(tube);
        this.entityManager.getTransaction().commit();
        return tube;
    }

    @Override
    public Tube getById(String id) {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT t FROM Tube t WHERE t.id=:id", Tube.class)
                .setParameter("id", id)
                .getSingleResult();

    }

    @Override
    public Tube getByName(String name) {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT t FROM Tube t WHERE t.name=:name", Tube.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public List<Tube> getAll() {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT t FROM Tube t", Tube.class)
                .getResultList();
    }
}
