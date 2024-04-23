package com.backend.mediacrunchbackend.domain.services;

import com.backend.mediacrunchbackend.domain.DTOs.DTOConverter;
import com.backend.mediacrunchbackend.domain.DTOs.MediaDTO;
import com.backend.mediacrunchbackend.domain.DTOs.RatingDTO;
import com.backend.mediacrunchbackend.domain.DTOs.UserDTO;
import com.backend.mediacrunchbackend.domain.exceptions.ResourceCreationException;
import com.backend.mediacrunchbackend.domain.exceptions.ResourceNotFoundException;
import com.backend.mediacrunchbackend.domain.models.Media;
import com.backend.mediacrunchbackend.domain.models.Rating;
import com.backend.mediacrunchbackend.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.backend.mediacrunchbackend.domain.DTOs.DTOConverter.convertUserToDTO;

@Service
public class UserService {
    private UserRepo userRepo;
    private MediaService mediaService;
    private RatingService ratingService;
    @Autowired
    public UserService(UserRepo userRepo,MediaService mediaService,RatingService ratingService) {
        this.userRepo = userRepo;
        this.mediaService = mediaService;
        this.ratingService = ratingService;
    }

    public User create(User user) {
       checkEmail(user.getEmail());
      return userRepo.save(user);

    }

    private void checkEmail(String email) {
        Optional<User> createdUser = userRepo.findByEmail(email);
        if(createdUser.isPresent()) {
            throw new ResourceCreationException("A user already exits with email " + email);
        }

    }

    public User getById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("No user with id: "+ id);
        }
        return user.get();
    }

    public User getUserByEmail(String email) {
        Optional<User> user =   userRepo.findByEmail(email);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("No user with email: "+email );
        }
        return user.get();
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public List<MediaDTO> getAllFromWatchlist(Long id) {
        User user = getById(id);
        UserDTO userDTO =  convertUserToDTO(user);
        return userDTO.getWatchlist();

    }

    public User addToWatchlist(Long id,Long mediaId) {
        User user = getById(id);
        Media media = mediaService.getById(mediaId);
        user.addToWatchList(media);
        mediaService.addToUserAdded(user,mediaId);
        return userRepo.save(user);

    }

    public UserDTO addRating(Double ratingValue, Long userId, Long mediaId) {
        User user = getById(userId);
        Media media = mediaService.getById(mediaId);
        Rating rating = new Rating(user,media,ratingValue);
        ratingService.create(rating);
        mediaService.addRating(rating,media.getId());
        userRepo.save(user);
        return convertUserToDTO(user);
    }

    public User updateUser(Long id,User userInfo) {
        User userToUpdate = getById(id);
        checkEmail(userInfo.getEmail());
        userToUpdate.setEmail(userInfo.getEmail());
        userToUpdate.setFirstName(userInfo.getFirstName());
        userToUpdate.setPassword(userInfo.getPassword());
        return userRepo.save(userToUpdate);
    }

    public Double getUserRating(Long userId,Long mediaId) {
        User user =  getById(userId);
        Media media = mediaService.getById(mediaId);
        Rating rating = ratingService.getUserRating(media,user);
        return rating.getRating();
    }













}
