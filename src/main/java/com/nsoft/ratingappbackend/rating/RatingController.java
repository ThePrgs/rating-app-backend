package com.nsoft.ratingappbackend.rating;

import com.nsoft.ratingappbackend.ratingsettings.RatingSettingsResponse;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettingsService;
import java.util.NoSuchElementException;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/rating")
public class RatingController {

	private final RatingService ratingService;
	private final RatingSettingsService ratingSettingsService;

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
	public ResponseEntity<String> updateRatingSettings(
		@Valid @RequestBody RatingSettingsResponse request) {

		try {
			boolean isUpdated = ratingSettingsService.updateRatingSettings(request);
			if (isUpdated) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping
	public ResponseEntity<String> createRating(@RequestBody RatingRequest request) {

		try {
			boolean response = ratingService.createRating(request);
			if (response) {
				return new ResponseEntity<>(HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}

		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
