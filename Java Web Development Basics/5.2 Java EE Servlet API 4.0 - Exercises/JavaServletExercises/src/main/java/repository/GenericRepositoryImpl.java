package repository;

import domain.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.List;

public class GenericRepositoryImpl implements ProductRepository {
    private EntityManager entityManager;

    public GenericRepositoryImpl() {
        this.entityManager = Persistence
                .createEntityManagerFactory("JavaServletExercises")
                .createEntityManager();
    }

    @Override
    public Product save(Product entity) {
        if (this.findByName(entity.getName()) == null) {
            this.entityManager.getTransaction().begin();
            this.entityManager.persist(entity);
            this.entityManager.getTransaction().commit();
        }
        return entity;
    }

    @Override
    public Product findById(String id) {
        this.entityManager.getTransaction().begin();
        Product product = this.entityManager
                .createQuery("SELECT p FROM products p WHERE p.id = :id", Product.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return product;
    }

    @Override
    public Product findByName(String name) {
        this.entityManager.getTransaction().begin();
        Product product;
        try {
            product = this.entityManager
                    .createQuery("SELECT p FROM products p WHERE p.name = :name", Product.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nre) {
            product = null;
        }
        this.entityManager.getTransaction().commit();
        return product;
    }

    @Override
    public List<Product> findAll() {
        this.entityManager.getTransaction().begin();
        List<Product> products = this.entityManager
                .createQuery("SELECT p FROM products p", Product.class)
                .getResultList();
        this.entityManager.getTransaction().commit();
        return products;
    }
}
