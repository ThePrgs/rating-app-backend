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

/**
 * Rating Controller has the api endpoints for getting settings /api/rating/settings , updating
 * settings /api/rating/settings and adding a rating /api/rating
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/rating")
public class RatingController {

	private final RatingService ratingService;
	private final RatingSettingsService ratingSettingsService;

	/**
	 * Method returns settings
	 *
	 * @return ResponseEntity
	 */
	@GetMapping("/current-settings")
	public ResponseEntity<RatingSettingsResponse> getRatingSettings() {

		try {
			RatingSettingsResponse response = ratingSettingsService.getRatingSettings();
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NoSuchElementException nse) {
			return new ResponseEntity<>(new RatingSettingsResponse(), HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Method takes request and changes settings using it
	 *
	 * @param request object containing new settings
	 * @return ResponseEntity
	 */


	@PutMapping("/settings")
	public ResponseEntity<String> updateRatingSettings(
		@Valid @RequestBody RatingSettingsResponse request) {

		try {
			boolean isUpdated = ratingSettingsService.updateRatingSettings(request);
			if (isUpdated) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * Method takes request containing new rating and sends it to the service
	 *
	 * @param request object of the rating
	 * @return ResponseEntity
	 */
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

	@GetMapping("/statistics")
	public ResponseEntity<RatingsBetweenDatesResponse> getRatingsBetweenDates(
		@Valid @RequestBody RatingsBetweenDatesRequest request) {
		RatingsBetweenDatesResponse response = new RatingsBetweenDatesResponse();
		try {
			if (ratingService.areDatesValid(request.getFirstDate(), request.getEndDate())) {
				// if difference between requested dates is 30 days
				response = ratingService.getRatingsBetweenDates(request);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setMessage("400 Bad Request! Difference between dates might be more than 30 days!");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (IllegalArgumentException e) {
			response.setMessage("400 Bad Request!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}

