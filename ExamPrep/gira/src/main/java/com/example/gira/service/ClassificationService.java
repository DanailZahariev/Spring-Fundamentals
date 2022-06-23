package com.example.gira.service;

import com.example.gira.model.entity.Classification;
import com.example.gira.model.entity.enums.ClassificationName;
import com.example.gira.repository.ClassificationRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ClassificationService {

    private final ClassificationRepository classificationRepository;

    public ClassificationService(ClassificationRepository classificationRepository) {
        this.classificationRepository = classificationRepository;
    }

    public void classificationInit() {
        if (classificationRepository.count() != 0) {
            return;
        }

        Arrays.stream(ClassificationName.values()).forEach(classificationName -> {

            Classification classification = new Classification();
            classification.setName(classificationName).setDescription("Description for " + classificationName.name().toLowerCase());

            classificationRepository.save(classification);
        });
    }
}
