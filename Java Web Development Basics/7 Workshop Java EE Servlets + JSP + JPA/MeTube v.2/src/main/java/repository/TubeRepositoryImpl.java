package repository;

import domain.entity.Tube;
import domain.enums.TubeStatus;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class TubeRepositoryImpl implements TubeRepository {
    private final EntityManager entityManager;

    @Inject
    public TubeRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("MeTubeV2")
                .createEntityManager();
    }

    @Override
    public void save(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(tube);
        this.entityManager.getTransaction().commit();
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
    public List<Tube> getAll() {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT t FROM Tube t", Tube.class)
                .getResultList();
    }

    @Override
    public Tube getByTitle(String title) {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT t FROM Tube t WHERE t.title=:title", Tube.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public List<Tube> getByUploaderId(String uploaderId) {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT t FROM Tube t WHERE t.uploader.id=:uploaderId", Tube.class)
                .setParameter("uploaderId", uploaderId)
                .getResultList();
    }

    @Override
    public Tube update(Tube tube) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(tube);
        this.entityManager.getTransaction().commit();
        return tube;
    }

    @Override
    public void incrementViews(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("UPDATE Tube t SET t.views=t.views+1 where t.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void updateStatus(String id, TubeStatus status){
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery("UPDATE Tube t SET t.status=:status WHERE t.id=:id")
                .setParameter("id", id)
                .setParameter("status", status)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
