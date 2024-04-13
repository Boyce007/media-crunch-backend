package com.backend.mediacrunchbackend.domain.services;

import com.backend.mediacrunchbackend.domain.models.Genre;
import com.backend.mediacrunchbackend.domain.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MediaRepo extends JpaRepository<Media,Long> {



    List<Media> findByGenre(Genre genre);


}
