package com.backend.mediacrunchbackend.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @JsonProperty("type")
    private MediaType type;
    private String image;
    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "watchlist", cascade = CascadeType.ALL)
    private List<User> addedToWatchList;


    @JsonProperty("Genre")
    private Genre genre;


    public Media(String title, List<Double> ratings,Date releaseDate,Genre genre,String image ) {
        this.title = title;
        this.ratings = ratings;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.image=image;
    }

    public void addRating(double rating) {
        ratings.add(rating);
    }


}

