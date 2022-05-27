package com.example.SpringMobilele.repository;

import com.example.SpringMobilele.models.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Long, Brand> {
}