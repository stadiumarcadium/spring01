import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Optional;

public class CustomerDao {
    private final EntityManagerFactory entityManagerFactory;

    public CustomerDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public Optional<Customer> findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            return Optional.ofNullable(entityManager.find(Customer.class, id));
        } finally {
            entityManager.close();
        }
    }

    public List<Order> orderByCustomerId(Long id) {
        Optional<Customer> customer = findById(id);
        return customer.get().getProducts();
    }
}
