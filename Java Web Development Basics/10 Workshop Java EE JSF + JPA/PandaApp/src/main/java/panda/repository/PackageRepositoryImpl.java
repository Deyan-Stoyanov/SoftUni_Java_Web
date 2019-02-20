package panda.repository;

import panda.domain.entity.Package;
import panda.domain.enums.Status;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public class PackageRepositoryImpl implements PackageRepository {
    private final EntityManager entityManager;

    @Inject
    public PackageRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Package save(Package aPackage) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(aPackage);
        this.entityManager.getTransaction().commit();
        return aPackage;
    }

    @Override
    public Package getById(String id) {
        return this.entityManager
                .createQuery("SELECT p FROM Package p WHERE p.id=:id", Package.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Package> getAll() {
        return this.entityManager
                .createQuery("SELECT p FROM Package p", Package.class)
                .getResultList();
    }

    @Override
    public Package update(Package aPackage) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(aPackage);
        this.entityManager.getTransaction().commit();
        return aPackage;
    }

    @Override
    public void updateStatus(String id, Status status) {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery("UPDATE Package p set p.status=:status WHERE p.id=:id")
                .setParameter("status", status)
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }

    @Override
    public void updateDeliveryDate(String id, LocalDateTime date) {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery("UPDATE Package p SET p.deliveryDate=:date WHERE p.id=:id")
                .setParameter("date", date)
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
