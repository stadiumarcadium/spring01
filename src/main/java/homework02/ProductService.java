package homework02;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) {
        return productRepository.findById(id);
    }

    public boolean isProductIdExist(Long id) {
        return productRepository.findById(id) != null;
    }

    public List<Product> getProductList() {
        return productRepository.findAll();
    }
}