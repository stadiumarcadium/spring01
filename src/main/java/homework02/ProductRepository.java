package homework02;

import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.*;

@Repository
public class ProductRepository {
    private Map<Long, Product> products;

    @PostConstruct
    public void init() {
        this.products = new HashMap<>();
        this.products.put(1L, new Product(1L, "Milk", 3.99));
        this.products.put(2L, new Product(2L, "Bread", 2.99));
        this.products.put(3L, new Product(3L, "Cheese", 9.99));
        this.products.put(4L, new Product(4L, "Water", 1.99));
        this.products.put(5L, new Product(5L, "Eggs", 4.99));
    }

    public List findAll() {
        return  new ArrayList<>(products.values());
    }

    public Product findById(Long id) {
        return products.get(id);
    }
}



