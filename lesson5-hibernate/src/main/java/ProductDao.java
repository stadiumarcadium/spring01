import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Optional;

public class ProductDao {
    private final EntityManagerFactory entityManagerFactory;

    public ProductDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Optional<Product> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Product.class, id));
        } finally {
            entityManager.close();
        }
    }

    public List<Product> findAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return entityManager.createQuery("Select u from Product u", Product.class).getResultList();

        } finally {
            entityManager.close();
        }
    }

    public void deleteById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.createQuery("Delete from Product u where u.id=:id").setParameter("id", id).executeUpdate();
            entityManager.getTransaction().commit();

        } catch (
                Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    public void saveOrUpdate(Product product) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            if (product.getId() != null) {
                entityManager.persist(product);
            } else {
                entityManager.merge(product);
            }
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
