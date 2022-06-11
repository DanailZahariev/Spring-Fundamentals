package bg.coffeeShop.service;

import bg.coffeeShop.models.entity.Category;
import bg.coffeeShop.models.entity.enums.CategoryName;
import bg.coffeeShop.repository.CategoryRepository;
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

        Arrays.stream(CategoryName.values()).forEach(categoryName -> {
            Category category = new Category();
            category.setName(categoryName);

            switch (categoryName) {
                case CAKE -> category.setNeededTime(10);
                case DRINK -> category.setNeededTime(1);
                case COFFEE -> category.setNeededTime(2);
                case OTHER -> category.setNeededTime(5);
            }
            categoryRepository.save(category);
        });
    }

    public Category findByCategoryNameEnum(CategoryName category) {
        return categoryRepository.findByName(category).orElse(null);
    }
}
