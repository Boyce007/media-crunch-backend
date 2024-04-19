package com.backend.mediacrunchbackend.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "User_TBl")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Rating> userRatings;
    @ManyToMany()
    @JoinTable(
            name = "user_media_watchlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id")
    )
    @JsonBackReference
    private List<Media> watchlist;


    public User(@NonNull String email, @NonNull String password, @NonNull String firstName, @NonNull String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.watchlist = new ArrayList<>();
        this.userRatings = new ArrayList<>();
    }

    public void addToWatchList(Media media) {
        watchlist.add(media);
    }

    public void removeFromWatchList(Media media) {
        watchlist.remove(media);
    }


}
