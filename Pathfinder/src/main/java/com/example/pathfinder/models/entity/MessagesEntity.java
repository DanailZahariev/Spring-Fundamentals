package com.example.pathfinder.models.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class MessagesEntity extends BasicEntity {

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "text_content", columnDefinition = "TEXT", nullable = false)
    private String textContent;

    @ManyToOne
    private UserEntity author;

    @ManyToOne
    private UserEntity recipient;

    public MessagesEntity() {
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public MessagesEntity setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getTextContent() {
        return textContent;
    }

    public MessagesEntity setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public MessagesEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public UserEntity getRecipient() {
        return recipient;
    }

    public MessagesEntity setRecipient(UserEntity recipient) {
        this.recipient = recipient;
        return this;
    }
}
