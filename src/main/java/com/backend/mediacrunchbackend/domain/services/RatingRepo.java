package com.backend.mediacrunchbackend.domain.services;

import com.backend.mediacrunchbackend.domain.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepo extends JpaRepository<Rating,Long> {
}
