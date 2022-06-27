package com.example.finalexam.service;

import com.example.finalexam.model.entity.Style;
import com.example.finalexam.model.entity.enums.StyleEnum;
import com.example.finalexam.repository.StyleRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleService {

    private final StyleRepository styleRepository;

    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public void initStyles() {

        if (styleRepository.count() != 0) {
            return;
        }
        Arrays.stream(StyleEnum.values()).forEach(styleEnum -> {
            Style style = new Style();
            style.setStyleName(styleEnum).setDescription("Description for " + styleEnum.name().toLowerCase());
            styleRepository.save(style);
        });
    }

    public Style findByStyleName(StyleEnum styleName) {
        return styleRepository.findByStyleName(styleName).orElse(null);
    }
}
