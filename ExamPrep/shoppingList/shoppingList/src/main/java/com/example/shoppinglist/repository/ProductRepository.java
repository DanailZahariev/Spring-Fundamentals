package com.example.shoppinglist.repository;

import com.example.shoppinglist.model.entity.Product;
import com.example.shoppinglist.model.entity.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    @Query("SELECT sum(p.price) FROM Product p")
    BigDecimal findTotalSum();

    List<Product> findAllByCategoryName(CategoryEnum categoryEnum);
}
