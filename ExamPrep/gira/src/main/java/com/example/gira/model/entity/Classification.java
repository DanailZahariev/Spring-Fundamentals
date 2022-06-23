package com.example.gira.model.entity;

import com.example.gira.model.entity.enums.ClassificationName;

import javax.persistence.*;

@Entity
@Table(name = "classifications")
public class Classification extends BasicEntity {

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private ClassificationName name;

    private String description;

    public Classification() {
    }

    public ClassificationName getName() {
        return name;
    }

    public Classification setName(ClassificationName name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Classification setDescription(String description) {
        this.description = description;
        return this;
    }
}
