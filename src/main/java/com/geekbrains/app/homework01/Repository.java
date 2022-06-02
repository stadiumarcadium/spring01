package com.geekbrains.app.homework01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private final Map<Long, Product> productMap = new HashMap<>();

    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }
    public void save(Long id, Product product) {
        productMap.put(id, product);
    }
}
