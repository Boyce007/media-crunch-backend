package com.backend.mediacrunchbackend.domain.DTOs;

import com.backend.mediacrunchbackend.domain.models.Media;
import lombok.Data;

import java.util.List;
@Data
public class UserDTO {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<RatingDTO> userRatings;
    private List<MediaDTO> watchlist;




}
