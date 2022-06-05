package homework02;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        this.products = new ArrayList<>();
        this.products.add(new Product(1L, "Milk", 3.99));
        this.products.add(new Product(2L, "Bread",2.99));
        this.products.add(new Product(3L, "Cheese", 9.99));
        this.products.add(new Product(4L, "Water", 1.99));
        this.products.add(new Product(5L, "Eggs", 4.99));
    }

    public List<Product> findAll() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}



