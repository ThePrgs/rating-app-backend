package com.nsoft.ratingappbackend.rating;

import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RatingRequest> create(@RequestBody RatingRequest request){
        return ratingService.create(request);

    }



}
