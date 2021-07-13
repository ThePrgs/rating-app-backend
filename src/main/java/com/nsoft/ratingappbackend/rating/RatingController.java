package com.nsoft.ratingappbackend.rating;

import com.nsoft.ratingappbackend.ratingsettings.RatingSettings;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettingsRepository;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettingsResponse;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettingsService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;


@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/rating")
public class RatingController {
    private final RatingService ratingService;
    private final RatingSettingsService ratingSettingsService;
    private final RatingSettingsRepository repo;

    @GetMapping("/settings")
    public ResponseEntity<RatingSettingsResponse> getRatingSettings() {

        try {
            RatingSettingsResponse response = ratingSettingsService.getRatingSettings();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (NoSuchElementException nse) {
            return new ResponseEntity<>(new RatingSettingsResponse(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/settings")
    public String updateRatingSettings(@Valid @RequestBody RatingSettingsResponse request) {
        try {

            Optional<RatingSettings> obj = repo.findById(1L);

            obj.get().setNumOfEmoticons(request.getNumOfEmoticons());
            obj.get().setTimeout(request.getTimeout());
            obj.get().setMsg(request.getMsg());

            repo.save(obj.get());

            return "Success";
        } catch (ConstraintViolationException e) {
            return "failed";
        }
    }

    @PostMapping
    public ResponseEntity<RatingRequest> create(@RequestBody RatingRequest request){
        try {
            return ratingService.create(request);
        } catch (NullPointerException e){
            return new ResponseEntity<>(request, HttpStatus.BAD_REQUEST);
        }
    }
}
