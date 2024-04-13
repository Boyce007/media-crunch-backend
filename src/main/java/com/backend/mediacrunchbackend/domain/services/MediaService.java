package com.backend.mediacrunchbackend.domain.services;

import com.backend.mediacrunchbackend.domain.exceptions.ResourceNotFoundException;
import com.backend.mediacrunchbackend.domain.models.Genre;
import com.backend.mediacrunchbackend.domain.models.Media;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaService {

    private MediaRepo mediaRepo;


    @Autowired
    public MediaService(MediaRepo mediaRepo) {
        this.mediaRepo = mediaRepo;
    }

    public Media create(Media media) {
        return mediaRepo.save(media);
    }

    public Double getRating(Long id) {
        Media media = getById(id);
        Double avg = 0.0;
        for (Double rating: media.getRatings()) {
            avg+=rating;
        }
        return avg/media.getRatings().size();


    }
    public Media addRating(double rating,Long id) {
        Media media =  getById(id);
        media.addRating(rating);
        return mediaRepo.save(media);
    }

    private Media getById(Long id) {
        Optional<Media>  media=  mediaRepo.findById(id);
        if(media.isEmpty()) {
            throw new ResourceNotFoundException("No media with id: " + id );
        }
        return media.get();
    }

    private List<Media> getAll() {
        return mediaRepo.findAll();
    }

    private List<Media> getAllByGenre(String genreName) {
        Genre genre  = Genre.valueOf(genreName);
        List<Media> mediaByGenre = mediaRepo.findByGenre(genre);

        return mediaByGenre;

    }

}
