package com.backend.mediacrunchbackend.domain.DTOs;

import com.backend.mediacrunchbackend.domain.models.Media;
import com.backend.mediacrunchbackend.domain.models.Rating;
import com.backend.mediacrunchbackend.domain.models.User;

import java.util.List;

public class DTOConverter {

    private static MediaDTO convertMediaToDTO(Media media) {
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setId(media.getId());
        mediaDTO.setTitle(media.getTitle());
        mediaDTO.setReleaseDate(media.getReleaseDate());
        mediaDTO.setType(media.getType());
        mediaDTO.setGenre(media.getGenre());
        mediaDTO.setImage(media.getImage());
        return mediaDTO;
    }

    private static RatingDTO convertRatingToDTO(Rating rating) {
        RatingDTO ratingDTO = new RatingDTO();
        ratingDTO.setId(rating.getId());
        ratingDTO.setRating(rating.getRating());
        return ratingDTO;
    }

    public static UserDTO convertUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        List<RatingDTO> ratingDTOs = user.getUserRatings().stream()
                .map(DTOConverter::convertRatingToDTO).toList();
        userDTO.setUserRatings(ratingDTOs);
        userDTO.setWatchlist(convertWatchlistToDTO(user.getWatchlist()));
        return userDTO;
    }

    public static List<MediaDTO> convertWatchlistToDTO(List<Media> media) {
        return media.stream().map(
                DTOConverter::convertMediaToDTO).toList();
    }

}
