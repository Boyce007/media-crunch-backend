package com.backend.mediacrunchbackend.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    private Double rating;

    public Rating(User user, Media media, Double rating) {
        this.user = user;
        this.media = media;
        this.rating = rating;
    }
}
