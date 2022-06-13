package com.battleships.service;

import com.battleships.model.entity.Category;
import com.battleships.model.entity.enums.CategoryEnum;
import com.battleships.repository.CategoryRepository;
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
                case CARGO -> category.setDescription("Cargo description");
                case BATTLE -> category.setDescription("Battle description");
                case PATROL -> category.setDescription("Patrol description");
            }
            categoryRepository.save(category);
        });
    }

    public Category findCategory(String category) {
        return categoryRepository.findByName(CategoryEnum.valueOf(category)).orElse(null);
    }
}
