package com.backend.mediacrunchbackend.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private List<Double> ratings;
    @JsonProperty("releaseDate")
    private Date releaseDate;

    @JsonProperty("Genre")
    private Genre genre;


    public Media(String title, List<Double> ratings, Genre genre) {
        this.title = title;
        this.ratings = ratings;
        this.genre = genre;
    }

    public void addRating(double rating) {
        ratings.add(rating);
    }




}

