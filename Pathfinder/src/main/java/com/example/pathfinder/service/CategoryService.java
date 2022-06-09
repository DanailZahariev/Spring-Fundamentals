package com.example.pathfinder.service;

import com.example.pathfinder.models.entity.CategoryEntity;
import com.example.pathfinder.models.entity.enums.CategoryName;
import com.example.pathfinder.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public CategoryEntity categoryNameEnum(CategoryName categoryEnum) {
        return categoryRepository.findByName(categoryEnum).orElse(null);
    }
}
