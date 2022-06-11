package bg.coffeeShop.service;

import bg.coffeeShop.models.entity.Order;
import bg.coffeeShop.models.service.OrderServiceModel;
import bg.coffeeShop.models.view.OrderViewModel;
import bg.coffeeShop.repository.OrderRepository;
import bg.coffeeShop.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;

    public OrderService(OrderRepository orderRepository, ModelMapper modelMapper,
                        CurrentUser currentUser, UserService userService,
                        CategoryService categoryService) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }


    public void addOrder(OrderServiceModel orderServiceModel) {
        Order order = modelMapper.map(orderServiceModel, Order.class);
        order.setEmployee(userService.findById(currentUser.getId()));
        order.setCategory(categoryService.findByCategoryNameEnum(orderServiceModel.getCategory()));

        orderRepository.save(order);
    }

    public List<OrderViewModel> findAllOrdersByPriceDesc() {
        return orderRepository.findAllOrderOrderByPriceDesc().stream().map(order ->
                modelMapper.map(order, OrderViewModel.class)).collect(Collectors.toList());
    }

    public void orderReady(Long id) {
        orderRepository.deleteById(id);
    }
}
