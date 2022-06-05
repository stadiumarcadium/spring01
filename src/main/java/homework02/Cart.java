package homework02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void add(Product product) {
        products.add(product);
    }

    public void removeByTitle(String productTitle) {
        products.removeIf(p -> p.getTitle().equals(productTitle));
    }
    public void removeById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public void clear() {
        products.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "products=" + products +
                '}';
    }
}
