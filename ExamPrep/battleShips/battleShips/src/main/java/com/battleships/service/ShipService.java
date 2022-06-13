package com.battleships.service;

import com.battleships.model.entity.Ship;
import com.battleships.model.entity.User;
import com.battleships.model.service.ShipServiceModel;
import com.battleships.model.service.UserServiceModel;
import com.battleships.repository.ShipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final ModelMapper modelMapper;


    public ShipService(ShipRepository shipRepository,
                       ModelMapper modelMapper) {
        this.shipRepository = shipRepository;
        this.modelMapper = modelMapper;
    }

    public void addShip(ShipServiceModel serviceModel, UserServiceModel currentUser) {
        Ship ship = modelMapper.map(serviceModel, Ship.class);
        ship.setUser(modelMapper.map(currentUser, User.class));
        shipRepository.save(ship);
    }
}
