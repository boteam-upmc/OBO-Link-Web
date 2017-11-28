package com.controller;

import com.domain.Video;
import com.exception.EntityException;
import com.repository.VideoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/{userId}/videos")
public class VideoController {
    private VideoRepository repository = new VideoRepository();

    @GetMapping(value = "/all")
    public List<Video> get(@PathVariable int userId) throws EntityException {
        return repository.findByUserId(userId);
    }

}
