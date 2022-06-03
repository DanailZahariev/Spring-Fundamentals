package com.example.SpringMobilele.models.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime created;

    private LocalDateTime modified;


    public Long getId() {
        return id;
    }

    public BasicEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public BasicEntity setModified(LocalDateTime modified) {
        this.modified = modified;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public BasicEntity setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }
}
