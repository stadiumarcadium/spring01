package com.geekbrains.app.homework01;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@WebListener
public class BootListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Repository productRepository = new Repository();
        final Map<Long, Product> productMap = new ConcurrentHashMap<>();
        productRepository.save(0L, new Product(9L, "product0", 100.00));
        productRepository.save(1L, new Product(0L, "product1", 110.00));
        productRepository.save(2L, new Product(1L, "product2", 120.00));
        productRepository.save(3L, new Product(2L, "product3", 130.00));
        productRepository.save(4L, new Product(3L, "product4", 140.00));
        productRepository.save(5L, new Product(4L, "product5", 150.00));
        productRepository.save(6L, new Product(5L, "product6", 160.00));
        productRepository.save(7L, new Product(6L, "product7", 170.00));
        productRepository.save(8L, new Product(7L, "product8", 180.00));
        productRepository.save(9L, new Product(8L, "product9", 190.00));
        sce.getServletContext().setAttribute("productRepository", productRepository);
    }
}