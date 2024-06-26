package com.backend.mediacrunchbackend.domain.DTOs;

import com.backend.mediacrunchbackend.domain.models.Media;
import com.backend.mediacrunchbackend.domain.models.Rating;
import com.backend.mediacrunchbackend.domain.models.User;

import java.util.List;

public class DTOConverter {

    public static MediaDTO convertMediaToDTO(Media media) {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setId(media.getId());
        mediaDTO.setTitle(media.getTitle());
        mediaDTO.setReleaseDate(media.getReleaseDate());
        mediaDTO.setType(media.getType());
        mediaDTO.setGenre(media.getGenre());
        mediaDTO.setImage(media.getImage());
        mediaDTO.setRating(media.getAverage());
        return mediaDTO;
    }

    public static RatingDTO convertRatingToDTO(Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(rating.getId());
        ratingDTO.setRating(rating.getRating());
        return ratingDTO;
    }

    public static UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPassword(user.getPassword());
        List<RatingDTO> ratingDTOs = user.getUserRatings().stream()
                .map(DTOConverter::convertRatingToDTO).toList();
        userDTO.setUserRatings(ratingDTOs);
        userDTO.setWatchlist(convertMediaListToDTO(user.getWatchlist()));
        return userDTO;
    }

    public static List<MediaDTO> convertMediaListToDTO(List<Media> media) {
        return media.stream().map(
                DTOConverter::convertMediaToDTO).toList();
    }

}
