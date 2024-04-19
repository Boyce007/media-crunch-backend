package com.backend.mediacrunchbackend.domain.controllers;

import com.backend.mediacrunchbackend.domain.models.Media;
import com.backend.mediacrunchbackend.domain.models.Rating;
import com.backend.mediacrunchbackend.domain.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
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
    @GetMapping(value = "/id/{id}")
    public Media getMediaById(@PathVariable Long id) {
        return mediaService.getById(id);
    }



    @GetMapping(value = "/{id}/rating")
    public String getRating(@PathVariable Long id) {
        return mediaService.getRating(id);

    }

    @GetMapping
    public List<Media> getAll() {
        return mediaService.getAll();
    }

    @GetMapping(value ="/genre/{genre}")
    public ResponseEntity<List<Media> > getALlByGenre(@PathVariable String genre) {
        try {
            List<Media> mediaByGenre = mediaService.getAllByGenre(genre);
            return new ResponseEntity<>(mediaByGenre,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/title/{title}")
    public ResponseEntity<Media>  getByTitle(@PathVariable String title) {
        try {
            Media media = mediaService.getByTitle(title);
            return new ResponseEntity<>(media,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
