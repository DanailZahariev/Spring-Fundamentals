package com.example.pathfinder.service;

import com.example.pathfinder.models.entity.RouteEntity;
import com.example.pathfinder.models.service.RouteServiceModel;
import com.example.pathfinder.models.view.RouteViewModel;
import com.example.pathfinder.repository.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;

    public RouteService(RouteRepository routeRepository, ModelMapper modelMapper,
                        UserService userService, CategoryService categoryService) {
        this.routeRepository = routeRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
    }


    public List<RouteViewModel> findAllRoutesView() {
        return routeRepository.findAll().stream()
                .map(routeEntity -> {
                    RouteViewModel routeViewModel = modelMapper.map(routeEntity, RouteViewModel.class);

                    if (routeEntity.getPictures().isEmpty()) {
                        routeViewModel.setPictureUrl("images/pic4.jpg");
                    }
                    routeViewModel.setPictureUrl(routeEntity.getPictures().stream().findFirst().get().getUrl());

                    return routeViewModel;
                }).collect(Collectors.toList());
    }

    public void addNewRoute(RouteServiceModel routeServiceModel) {

        RouteEntity route = modelMapper.map(routeServiceModel, RouteEntity.class);
        route.setAuthor(userService.findCurrentLoggedUser());
        route.setCategories(routeServiceModel.getCategories()
                .stream()
                .map(categoryService::categoryNameEnum).collect(Collectors.toSet()));
        routeRepository.save(route);
    }
}
