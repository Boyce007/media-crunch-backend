package com.backend.mediacrunchbackend.domain.DTOs;

import com.backend.mediacrunchbackend.domain.models.Genre;
import com.backend.mediacrunchbackend.domain.models.MediaType;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MediaDTO {
    private Long id;
    private String title;
    private Date releaseDate;
    private MediaType type;
    private String image;
    private Genre genre;
    private List<RatingDTO> ratings;
    // Other fields as needed
}
