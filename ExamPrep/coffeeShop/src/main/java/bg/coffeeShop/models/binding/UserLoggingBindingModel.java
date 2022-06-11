package bg.coffeeShop.models.binding;

import javax.validation.constraints.Size;

public class UserLoggingBindingModel {

    @Size(min = 5, max = 20)
    private String username;
    @Size(min = 3)
    private String password;

    public UserLoggingBindingModel() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoggingBindingModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoggingBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }
}
