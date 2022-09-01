package ru.mal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public class Product {
    private Long id;
    @NotBlank(message = "can not be empty")
    private String title;
    @Digits(integer = 10, fraction = 2, message = "cost format $.¢¢")
    private BigDecimal cost;

    public Product(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
