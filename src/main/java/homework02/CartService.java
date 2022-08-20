package homework02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;

import javax.annotation.PostConstruct;

@Component
@Service
@Scope("prototype")
public class CartService {
    private ProductService productService;
    private Cart cart;


    public CartService(ProductService productService) {
        this.productService = productService;
    }

    @PostConstruct
    public void init() {
        this.cart = new Cart();
    }

    public Cart getCurrentCart() {
        return cart;
    }

    public void addToCartByProductId(Long productId) {
        Product product = productService.findById(productId);
        cart.add(product);
    }

    public void clearCurrentCart() {
        cart.clear();
    }

    public void removeFromCart(String productTitle) {
        cart.removeByTitle(productTitle);
    }

    public void removeFromCartId(Long id) {
        cart.removeById(id);

    }

    public void printCart() {
        for (Product product : cart.getProducts()) {
            System.out.printf("Product: id = %-2s | name = %-15s | price = %-8s\n",
                    product.getId(), product.getTitle(), product.getCost());
        }
        System.out.println("\nСтоимость корзины: " + getSum());
    }

    public Double getSum() {
        Double sum = 0.0;
        for (Product product : cart.getProducts()) {
            sum += product.getCost();
        }
        return sum;
    }

    public boolean isProductIdExist(Long id) {
        return cart.getProducts().stream().anyMatch(p -> p.getId().equals(id));
    }
}
