package com.geekbrains.app.homework01;

public class Product {
    private Long id;
    private String title;
    private Double cost;

    public Product() {
    }

    public Product(Long id, String title, Double cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Double getCost() {
        return cost;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
