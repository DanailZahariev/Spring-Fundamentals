package com.example.finalexam.repository;

import com.example.finalexam.model.entity.Style;
import com.example.finalexam.model.entity.enums.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {

    Optional<Style> findByStyleName(StyleEnum styleName);
}
