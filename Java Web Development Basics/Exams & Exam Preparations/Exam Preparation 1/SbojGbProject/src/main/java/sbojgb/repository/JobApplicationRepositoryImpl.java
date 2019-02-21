package sbojgb.repository;

import sbojgb.domain.entity.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobApplicationRepositoryImpl implements JobApplicationRepository {
    private final EntityManager entityManager;

    @Inject
    public JobApplicationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public JobApplication save(JobApplication jobApplication) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(jobApplication);
        this.entityManager.getTransaction().commit();
        return jobApplication;
    }

    @Override
    public JobApplication getById(String id) {
        return this.entityManager
                .createQuery("SELECT j FROM JobApplication j WHERE j.id=:id", JobApplication.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<JobApplication> getAll() {
        return this.entityManager
                .createQuery("SELECT j FROM JobApplication j", JobApplication.class)
                .getResultList();
    }

    @Override
    public void delete(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager
                .createQuery("DELETE FROM JobApplication j WHERE j.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
