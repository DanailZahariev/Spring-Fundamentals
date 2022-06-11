package bg.coffeeShop.models.service;

import bg.coffeeShop.models.entity.User;
import bg.coffeeShop.models.entity.enums.CategoryName;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderServiceModel {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private LocalDateTime orderTime;
    private CategoryName category;
    private User employee;

    public OrderServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public OrderServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderServiceModel setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public CategoryName getCategory() {
        return category;
    }

    public OrderServiceModel setCategory(CategoryName category) {
        this.category = category;
        return this;
    }

    public User getEmployee() {
        return employee;
    }

    public OrderServiceModel setEmployee(User employee) {
        this.employee = employee;
        return this;
    }
}
