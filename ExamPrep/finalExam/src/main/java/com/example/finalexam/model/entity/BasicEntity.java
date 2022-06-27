package com.example.finalexam.model.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public BasicEntity() {
    }

    public Long getId() {
        return id;
    }

    public BasicEntity setId(Long id) {
        this.id = id;
        return this;
    }
}
