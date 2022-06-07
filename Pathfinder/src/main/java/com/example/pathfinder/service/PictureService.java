package com.example.pathfinder.service;

import com.example.pathfinder.repository.PictureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }


    public List<String> findAllUrl() {
        return pictureRepository.findAllUrl();
    }
}
