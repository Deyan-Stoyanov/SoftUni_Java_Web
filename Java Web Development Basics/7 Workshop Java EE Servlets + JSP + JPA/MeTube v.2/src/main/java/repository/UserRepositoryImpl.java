package repository;

import domain.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("MeTubeV2")
                .createEntityManager();
    }

    @Override
    public void save(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public User getById(String id) {
        this.entityManager.getTransaction().begin();
        User user = this.entityManager
                .createQuery("SELECT t FROM User t WHERE t.id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.refresh(user);
        this.entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public List<User> getAll() {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT t FROM User t", User.class)
                .getResultList();
    }

    @Override
    public User getByUsername(String username) {
        this.entityManager.getTransaction().begin();
        try {
            return this.entityManager
                    .createQuery("SELECT u FROM User u WHERE u.username=:username", User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        this.entityManager.getTransaction().begin();
        try {
            return this.entityManager
                    .createQuery("SELECT u FROM User u WHERE u.username=:username " +
                            "AND u.password=:password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException nre) {
            return null;
        } finally {
            this.entityManager.getTransaction().commit();
        }
    }
}

