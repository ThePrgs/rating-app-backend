package com.example.ratingappbackend.rating;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
@RequestMapping("/api/rating")
public class RatingController {
    private final RatingService ratingService;

    @PostMapping
    public String create(@RequestBody RatingRequest request){
        return ratingService.create(request);

    }



}
