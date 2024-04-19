package com.backend.mediacrunchbackend.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;
import java.util.Random;


@NoArgsConstructor
@Data
@Entity
public class Media implements Comparable<Media>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "media")
    @JsonBackReference
    private List<Rating> ratings;
    @Temporal(TemporalType.DATE)
    private Date releaseDate;
    @JsonProperty("type")
    private MediaType type;
    private String image;

    @ManyToMany(mappedBy = "watchlist", cascade = CascadeType.ALL)
    private List<User> addedToWatchList;



    @JsonProperty("Genre")
    private Genre genre;


    public Media(String title,Date releaseDate,Genre genre,String image ) {
        this.title = title;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.image=image;
    }

    public void addRating(Rating rating) {
        ratings.add(rating);
    }

    public Double getAverage() {
        Double avg = 0.0;
        for (Rating rating: getRatings()) {
            avg+=rating.getRating();
        }

        return avg/getRatings().size();
    }
    @Override
    public int compareTo(Media otherMedia) {
        return Double.compare(this.getAverage(), otherMedia.getAverage());
    }




}

