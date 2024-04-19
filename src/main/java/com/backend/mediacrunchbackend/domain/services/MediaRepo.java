package com.backend.mediacrunchbackend.domain.services;

import com.backend.mediacrunchbackend.domain.models.Genre;
import com.backend.mediacrunchbackend.domain.models.Media;
import com.backend.mediacrunchbackend.domain.models.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MediaRepo extends JpaRepository<Media,Long> {



    List<Media> findByGenre(Genre genre);
    Optional<Media> findByTitle(String title);

    List<Media> findByReleaseDate(Date date);
    Optional<List<Media>> findByType(MediaType mediaType);


}
