package metube.repository;

import metube.domain.entity.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;

    @Inject
    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User save(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(user);
        this.entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User update(User user) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(user);
        this.entityManager.getTransaction().commit();
        return user;
    }

    @Override
    public User getById(String id) {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT u FROM User u WHERE u.id=:id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User getByUsername(String username) {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT u FROM User u WHERE u.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public List<User> getAll() {
        this.entityManager.clear();
        return this.entityManager
                .createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }
}
