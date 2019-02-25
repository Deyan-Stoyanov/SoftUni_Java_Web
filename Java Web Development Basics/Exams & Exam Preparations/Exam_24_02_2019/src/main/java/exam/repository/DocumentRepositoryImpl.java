package exam.repository;

import exam.domain.entity.Document;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepository {
    private final EntityManager entityManager;

    @Inject
    public DocumentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Document save(Document document) {
        this.entityManager.getTransaction().begin();
        try {
            this.entityManager.persist(document);
            this.entityManager.getTransaction().commit();
            return document;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Document update(Document document) {
        this.entityManager.getTransaction().begin();
        try {
            Document updatedDocument = this.entityManager.merge(document);
            this.entityManager.getTransaction().commit();
            return updatedDocument;
        } catch (Exception e) {
            this.entityManager.getTransaction().rollback();
            return null;
        }
    }

    @Override
    public Document findById(String id) {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT d FROM Document d WHERE d.id=:id", Document.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Document findByTitle(String title) {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT d FROM Document d WHERE d.title=:title", Document.class)
                .setParameter("title", title)
                .getSingleResult();
    }

    @Override
    public List<Document> findAll() {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT d FROM Document d", Document.class)
                .getResultList();
    }

    @Override
    public void delete(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery("DELETE FROM Document d WHERE d.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
