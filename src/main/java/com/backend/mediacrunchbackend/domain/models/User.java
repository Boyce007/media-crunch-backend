package com.backend.mediacrunchbackend.domain.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
    @ManyToMany()
    @JoinTable(
            name = "user_media_watchlist",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id")
    )
    private List<Media> watchlist;

    public User(@NonNull String email, @NonNull String password, List<Media> watchlist) {
        this.email = email;
        this.password = password;
        this.watchlist = watchlist;
    }

    public void addToWatchList(Media media) {
        watchlist.add(media);
    }

    public void removeFromWatchList(Media media) {
        watchlist.remove(media);
    }





}
