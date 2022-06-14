package com.example.shoppinglist.service;

import com.example.shoppinglist.model.entity.Category;
import com.example.shoppinglist.model.entity.enums.CategoryEnum;
import com.example.shoppinglist.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void initCategories() {
        if (categoryRepository.count() != 0) {
            return;
        }

        Arrays.stream(CategoryEnum.values()).forEach(categoryEnum -> {

            Category category = new Category();
            category.setName(categoryEnum);

            switch (categoryEnum) {
                case DRINK -> category.setDescription("Drink description...");
                case FOOD -> category.setDescription("Food description...");
                case HOUSEHOLD -> category.setDescription("Household description...");
                case OTHER -> category.setDescription("Other description");
            }
            categoryRepository.save(category);
        });
    }
}
