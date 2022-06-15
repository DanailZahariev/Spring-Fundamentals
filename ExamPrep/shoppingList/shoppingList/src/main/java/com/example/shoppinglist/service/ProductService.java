package com.example.shoppinglist.service;

import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.model.entity.enums.CategoryEnum;
import com.example.shoppinglist.model.service.ProductServiceModel;
import com.example.shoppinglist.model.view.ProductView;
import com.example.shoppinglist.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository,
                          CategoryService categoryService,
                          ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    public ProductServiceModel findByName(String name) {
        return modelMapper.map(productRepository.findByName(name), ProductServiceModel.class);
    }

    public void addProduct(ProductServiceModel productServiceModel) {
        Product product = modelMapper.map(productServiceModel, Product.class);

        product.setCategory(categoryService.findByName(productServiceModel.getCategory()));

        productRepository.save(product);
    }

    public List<ProductView> findAllByCategoryName(CategoryEnum categoryEnum) {
        return productRepository.findAllByCategoryName(categoryEnum)
                .stream().map(product -> modelMapper.map(product, ProductView.class))
                .collect(Collectors.toList());
    }

    public BigDecimal getTotalSum() {
        return productRepository.findTotalSum();
    }

    public void buyProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void buyAllProducts() {
        productRepository.deleteAll();
    }
}
