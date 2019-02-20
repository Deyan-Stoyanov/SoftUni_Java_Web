package panda.repository;

import panda.domain.entity.Receipt;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class ReceiptRepositoryImpl implements ReceiptRepository {
    private final EntityManager entityManager;

    @Inject
    public ReceiptRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Receipt save(Receipt receipt) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(receipt);
        this.entityManager.getTransaction().commit();
        return receipt;
    }

    @Override
    public Receipt getById(String id) {
        return this.entityManager
                .createQuery("SELECT r FROM Receipt r WHERE r.id=:id", Receipt.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Receipt> getAll() {
        return this.entityManager
                .createQuery("SELECT r FROM Receipt r", Receipt.class)
                .getResultList();
    }
}
