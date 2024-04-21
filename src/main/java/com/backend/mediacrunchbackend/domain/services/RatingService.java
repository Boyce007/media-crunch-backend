package com.backend.mediacrunchbackend.domain.services;

import com.backend.mediacrunchbackend.domain.exceptions.ResourceNotFoundException;
import com.backend.mediacrunchbackend.domain.models.Media;
import com.backend.mediacrunchbackend.domain.models.Rating;
import com.backend.mediacrunchbackend.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {
    RatingRepo ratingRepo;
    @Autowired
    public RatingService(RatingRepo ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    public Rating create(Rating rating) {
        return ratingRepo.save(rating);
    }

    public Rating getById(Long id) {
       Optional<Rating> rating = ratingRepo.findById(id);
       if (rating.isEmpty()) {
           throw new ResourceNotFoundException("No rating with id:" + id);
       }
       return rating.get();
    }

    public Boolean removeRating(Long id) {
       Rating rating =  getById(id);
       ratingRepo.delete(rating);
       return true;
    }

    public Rating getUserRating(Media media, User user) {
        Optional<Rating> rating = ratingRepo.findByMediaIdAndUserId(user,media);
        if(rating.isEmpty()) {
            throw new ResourceNotFoundException("No rating for media " + media.getId() + " from user " + user.getId());
        }
        return rating.get();
    }


}
