package com.backend.mediacrunchbackend.domain.controllers;

import com.backend.mediacrunchbackend.domain.models.Media;
import com.backend.mediacrunchbackend.domain.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/media")
public class MediaController {
    private MediaService mediaService;


    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService=mediaService;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Media create(@RequestBody Media media) {
        return mediaService.create(media);
    }
}
