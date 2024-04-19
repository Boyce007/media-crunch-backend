package com.backend.mediacrunchbackend.domain.services;

import com.backend.mediacrunchbackend.domain.exceptions.ResourceNotFoundException;
import com.backend.mediacrunchbackend.domain.models.Genre;
import com.backend.mediacrunchbackend.domain.models.Media;
import com.backend.mediacrunchbackend.domain.models.Rating;
import com.backend.mediacrunchbackend.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Collections;
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

    public String getRating(Long id) {
        Media media = getById(id);
        Double avg = media.getAverage();

        return String.format("%.2g%n",avg);


    }
    public Media addRating(Rating rating, Long id) {
        Media media =  getById(id);
        media.addRating(rating);
        return mediaRepo.save(media);
    }

    public Media getById(Long id) {
        Optional<Media>  media=  mediaRepo.findById(id);
        if(media.isEmpty()) {
            throw new ResourceNotFoundException("No media with id: " + id );
        }
        return media.get();
    }

    public List<Media> getAll() {
        return mediaRepo.findAll();
    }

    public List<Media> getAllByGenre(String genreName) {
        genreName = genreName.toUpperCase();
        Genre genre  = Genre.valueOf(genreName);
        return mediaRepo.findByGenre(genre);
    }

    public Media getByTitle(String title) {
        Optional<Media> media = mediaRepo.findByTitle(title);
        if(media.isEmpty()) {
            throw new ResourceNotFoundException("No title with: " +title);
        }
        return media.get();

    }

    public void addToUserAdded(User user,Long id) {
        Media media = getById(id);
        media.getAddedToWatchList().add(user);
        mediaRepo.save(media);
    }

    public List<Media> getTopRated() {
        List<Media> allMedia = mediaRepo.findAll();
        Collections.sort(allMedia);
        return allMedia.subList(0,10);

    }

}
