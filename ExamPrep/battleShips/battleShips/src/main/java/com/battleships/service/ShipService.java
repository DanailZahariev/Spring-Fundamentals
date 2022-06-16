package com.battleships.service;

import com.battleships.model.binding.HomeBindingModel;
import com.battleships.model.entity.Ship;
import com.battleships.model.entity.User;
import com.battleships.model.service.ShipServiceModel;
import com.battleships.model.service.UserServiceModel;
import com.battleships.model.view.ShipViewModel;
import com.battleships.repository.ShipRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ShipViewModel> findAll() {
        return shipRepository.findAll().stream().map(s -> modelMapper.map(s, ShipViewModel.class))
                .sorted(Comparator.comparing(ShipViewModel::getName))
                .sorted(Comparator.comparing(ShipViewModel::getHealth))
                .sorted(Comparator.comparing(ShipViewModel::getPower))
                .collect(Collectors.toList());
    }

    public void fight(HomeBindingModel homeBindingModel) {
        Optional<Ship> attacker = shipRepository.findById(homeBindingModel.getAttackerShipId());
        Optional<Ship> defender = shipRepository.findById(homeBindingModel.getDefenderShipId());

        if (attacker.isEmpty() || defender.isEmpty()) {
            return;
        }

        Ship defShip = defender.get();

        defShip.setHealth(defShip.getHealth() - attacker.get().getPower());

        if (defShip.getHealth() <= 0) {
            shipRepository.delete(defShip);
            return;
        }

        shipRepository.save(defShip);
    }
}
