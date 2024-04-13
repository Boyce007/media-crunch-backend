package com.backend.mediacrunchbackend.domain.models;

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
    private Date releaseDate;
    private Genre genre;


    public void addRating(double rating) {
        ratings.add(rating);
    }




}

