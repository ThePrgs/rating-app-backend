package com.nsoft.ratingappbackend.rating;

import com.nsoft.ratingappbackend.rating.payload.RatingRequest;
import com.nsoft.ratingappbackend.rating.payload.RatingResponse;
import com.nsoft.ratingappbackend.rating.payload.RatingsBetweenDatesRequest;
import com.nsoft.ratingappbackend.rating.payload.RatingsBetweenDatesResponse;
import com.nsoft.ratingappbackend.ratingsettings.payload.RatingSettingsRequest;
import com.nsoft.ratingappbackend.ratingsettings.payload.RatingSettingsResponse;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettingsService;
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
		RatingSettingsResponse response = new RatingSettingsResponse();
		try {
			response = ratingSettingsService.getRatingSettings();
			if(response.getRatingSettings() != null) {

				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMessage("An error has occurred!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
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
		@Valid @RequestBody RatingSettingsRequest request) {
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
	public ResponseEntity<RatingResponse> createRating(@Valid @RequestBody RatingRequest request) {
			RatingResponse response = new RatingResponse();
		try {
			response = ratingService.createRating(request);
			if(response.getRating() != null) {
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			} else {
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setMessage(HttpStatus.BAD_REQUEST.toString());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/statistics")
	public ResponseEntity<RatingsBetweenDatesResponse> getRatingsBetweenDates(
		@Valid @RequestBody RatingsBetweenDatesRequest request) {
		RatingsBetweenDatesResponse response = new RatingsBetweenDatesResponse();
		try {
			if (ratingService.areDatesValid(request.getStartDate(), request.getEndDate())) {
				// if difference between requested dates is 30 days
				response = ratingService.getRatingsBetweenDates(request);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setMessage(HttpStatus.BAD_REQUEST + "! Difference between dates might be more than 30 days!");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (IllegalArgumentException e) {
			response.setMessage(HttpStatus.BAD_REQUEST.toString());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}

