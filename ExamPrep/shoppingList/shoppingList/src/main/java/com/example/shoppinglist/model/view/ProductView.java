package com.example.shoppinglist.model.view;

import java.math.BigDecimal;

public class ProductView {

    private String id;
    private String name;
    private BigDecimal price;

    public ProductView() {
    }

    public String getId() {
        return id;
    }

    public ProductView setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductView setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductView setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
