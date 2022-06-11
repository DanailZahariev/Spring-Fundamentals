package bg.coffeeShop.web;

import bg.coffeeShop.models.binding.OrderAddBindingModel;
import bg.coffeeShop.models.service.OrderServiceModel;
import bg.coffeeShop.service.OrderService;
import bg.coffeeShop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/orders")
public class OderController {

    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public OderController(OrderService orderService, ModelMapper modelMapper, UserService userService) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String addOffer() {
        if (userService.userIsNotLogged()) {
            return "redirect:/";
        }

        return "order-add";
    }

    @PostMapping("/add")
    public String addOrder(@Valid OrderAddBindingModel orderAddBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("orderAddBindingModel", orderAddBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddBindingModel", bindingResult);

            return "redirect:add";
        }

        orderService.addOrder(modelMapper.map(orderAddBindingModel, OrderServiceModel.class));

        return "redirect:/";
    }

    @ModelAttribute
    public OrderAddBindingModel orderAddBindingModel() {
        return new OrderAddBindingModel();
    }
}
