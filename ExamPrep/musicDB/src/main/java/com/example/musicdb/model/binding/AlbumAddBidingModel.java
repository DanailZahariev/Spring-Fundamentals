package com.example.musicdb.model.binding;

import com.example.musicdb.model.entity.enums.ArtistName;
import com.example.musicdb.model.entity.enums.GenreEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AlbumAddBidingModel {

    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

    @NotBlank
    @Size(min = 5)
    private String imageUrl;

    @PositiveOrZero
    private BigDecimal price;

    @Min(10)
    private Integer copies;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private String producer;

    @NotNull
    private GenreEnum genre;

    @NotNull
    private ArtistName artist;

    @NotBlank
    @Size(min = 5)
    private String description;

    public AlbumAddBidingModel() {
    }

    public String getName() {
        return name;
    }

    public AlbumAddBidingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AlbumAddBidingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AlbumAddBidingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Integer getCopies() {
        return copies;
    }

    public AlbumAddBidingModel setCopies(Integer copies) {
        this.copies = copies;
        return this;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AlbumAddBidingModel setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getProducer() {
        return producer;
    }

    public AlbumAddBidingModel setProducer(String producer) {
        this.producer = producer;
        return this;
    }

    public GenreEnum getGenre() {
        return genre;
    }

    public AlbumAddBidingModel setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    public ArtistName getArtist() {
        return artist;
    }

    public AlbumAddBidingModel setArtist(ArtistName artist) {
        this.artist = artist;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AlbumAddBidingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
