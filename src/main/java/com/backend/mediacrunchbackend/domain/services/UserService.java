package com.backend.mediacrunchbackend.domain.services;

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
import java.util.stream.Collectors;

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
      return userRepo.save(user);

    }

    public User getById(Long id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty()) {
            throw new ResourceNotFoundException("No user with id: "+ id);
        }
        return user.get();
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public List<MediaDTO> getAllFromWatchlist(Long id) {
        User user = getById(id);
        UserDTO userDTO = convertUserToDTO(user);
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
        mediaService.addRating(rating,media.getId());
        userRepo.save(user);

        return convertUserToDTO(user);
    }

    private MediaDTO convertMediaToDTO(Media media) {
        MediaDTO mediaDTO = new MediaDTO();
        // Set MediaDTO properties from Media entity
        mediaDTO.setId(media.getId());
        mediaDTO.setTitle(media.getTitle());
        mediaDTO.setReleaseDate(media.getReleaseDate());
        mediaDTO.setType(media.getType());
        mediaDTO.setGenre(media.getGenre());
        mediaDTO.setImage(media.getImage());
        return mediaDTO;
    }

    private RatingDTO convertRatingToDTO(Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(rating.getId());
        ratingDTO.setRating(rating.getRating());
        return ratingDTO;
    }

    public UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        List<RatingDTO> ratingDTOs = user.getUserRatings().stream()
                .map(this::convertRatingToDTO).toList();
        userDTO.setUserRatings(ratingDTOs);
        userDTO.setWatchlist(convertWatchlistToDTO(user.getWatchlist()));

        return userDTO;
    }

    public List<MediaDTO> convertWatchlistToDTO(List<Media> media) {
        List<MediaDTO> mediaDTOS = media.stream().map(
                this::convertMediaToDTO).toList();
        return mediaDTOS;
    }








}
