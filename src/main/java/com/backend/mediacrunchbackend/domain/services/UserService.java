package com.backend.mediacrunchbackend.domain.services;

import com.backend.mediacrunchbackend.domain.exceptions.ResourceCreationException;
import com.backend.mediacrunchbackend.domain.exceptions.ResourceNotFoundException;
import com.backend.mediacrunchbackend.domain.models.Media;
import com.backend.mediacrunchbackend.domain.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private UserRepo userRepo;
    private MediaService mediaService;
    @Autowired
    public UserService(UserRepo userRepo,MediaService mediaService) {
        this.userRepo = userRepo;
        this.mediaService = mediaService;
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

    public List<Media> getAllFromWatchlist(Long id) {
        User user = getById(id);
        return user.getWatchlist();

    }

    public User addToWatchlist(Long id,Long mediaId) {
        User user = getById(id);
        Media media = mediaService.getById(mediaId);
        user.addToWatchList(media);
        mediaService.addToUserAdded(user,mediaId);
        return userRepo.save(user);
    }






}
