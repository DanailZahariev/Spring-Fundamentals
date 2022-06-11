package bg.coffeeShop.models.binding;

import bg.coffeeShop.models.entity.enums.CategoryName;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderAddBindingModel {

    @NotNull
    @Size(min = 3, max = 20)
    private String name;
    @NotNull
    @Positive
    private BigDecimal price;
    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderTime;
    @NotNull
    private CategoryName category;
    @Size(min = 5)
    private String description;

    public OrderAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public OrderAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderAddBindingModel setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public CategoryName getCategory() {
        return category;
    }

    public OrderAddBindingModel setCategory(CategoryName category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
