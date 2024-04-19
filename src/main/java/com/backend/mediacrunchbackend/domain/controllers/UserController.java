package com.backend.mediacrunchbackend.domain.controllers;

import com.backend.mediacrunchbackend.domain.DTOs.RatingDTO;
import com.backend.mediacrunchbackend.domain.DTOs.UserDTO;
import com.backend.mediacrunchbackend.domain.models.Media;
import com.backend.mediacrunchbackend.domain.models.Rating;
import com.backend.mediacrunchbackend.domain.models.User;
import com.backend.mediacrunchbackend.domain.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    public User createUser(@RequestBody User user) {
//        try {
//            return new ResponseEntity<>(userService.create(user),HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
        return userService.create(user);
    }
    @GetMapping(value ="id/{id}")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.convertUserToDTO(userService.getById(id)) ;
    }
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    @GetMapping(value = "{id}/watchlist")
    public List<Media> getAllFromWatchList(@PathVariable Long id) {
        return userService.getAllFromWatchlist(id);
    }

    @PutMapping(value = "{id}/addToWatch/{mediaId}")
    public ResponseEntity<Boolean> addToWatchList(@PathVariable Long id,@PathVariable Long mediaId) {
        try {
            userService.addToWatchlist(id,mediaId);
            return new ResponseEntity<>(true,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value= "{userId}/rate/{mediaId}/{rating}")
    public ResponseEntity<UserDTO> rate(@PathVariable Long userId, @PathVariable Long mediaId, @PathVariable Double rating) {
        UserDTO userDTO = userService.addRating(rating,userId,mediaId);
        return ResponseEntity.ok(userDTO);



    }




}
