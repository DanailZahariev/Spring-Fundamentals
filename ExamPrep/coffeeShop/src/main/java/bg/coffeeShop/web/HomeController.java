package bg.coffeeShop.web;

import bg.coffeeShop.models.view.OrderViewModel;
import bg.coffeeShop.service.OrderService;
import bg.coffeeShop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final UserService userService;
    private final OrderService orderService;

    public HomeController(UserService userService, OrderService orderService) {
        this.userService = userService;
        this.orderService = orderService;
    }

    @GetMapping
    public String index(Model model) {

        if (userService.userIsNotLogged()) {

            return "index";
        }

        List<OrderViewModel> orders = orderService.findAllOrdersByPriceDesc();
        int totalTime = orders.stream().mapToInt(o -> o.getCategory().getNeededTime()).sum();

        model.addAttribute("orders", orders)
                .addAttribute("totalTime", totalTime)
                .addAttribute("users", userService.findAllUserAndCountOfOrders());

        return "home";
    }

    @GetMapping("/ready/{id}")
    public String ready(@PathVariable Long id) {

        orderService.orderReady(id);

        return "redirect:/";
    }

}
