package bg.coffeeShop.models.view;

public class UserViewModel {

    private String username;
    private Integer orderCount;

    public UserViewModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public UserViewModel setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
        return this;
    }
}
