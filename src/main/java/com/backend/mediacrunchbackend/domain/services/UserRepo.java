package com.backend.mediacrunchbackend.domain.services;

import com.backend.mediacrunchbackend.domain.models.Media;
import com.backend.mediacrunchbackend.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);




}
