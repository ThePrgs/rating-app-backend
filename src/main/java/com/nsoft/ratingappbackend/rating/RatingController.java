package com.nsoft.ratingappbackend.rating;

import com.nsoft.ratingappbackend.rating.payload.RatingRequest;
import com.nsoft.ratingappbackend.rating.payload.RatingResponse;
import com.nsoft.ratingappbackend.rating.payload.RatingsBetweenDatesRequest;
import com.nsoft.ratingappbackend.rating.payload.RatingsBetweenDatesResponse;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettingsService;
import com.nsoft.ratingappbackend.ratingsettings.payload.RatingSettingsRequest;
import com.nsoft.ratingappbackend.ratingsettings.payload.RatingSettingsResponse;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
 * RatingController - a rest controller for ratings. This controller contains API endpoints used for
 * creating ratings, settings and more.
 *
 * @see Rating
 * @see com.nsoft.ratingappbackend.ratingsettings.RatingSettings
 */
@RestController
@AllArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping("/api/rating")
public class RatingController {

	private final RatingService ratingService;
	private final RatingSettingsService ratingSettingsService;

	/**
	 * API endpoint - get current rating settings.
	 *
	 * @return ResponseEntity with a RatingSettingsResponse.
	 */
	@GetMapping("/current-settings")
	public ResponseEntity<RatingSettingsResponse> getRatingSettings() {
		RatingSettingsResponse response = new RatingSettingsResponse();
		try {
			log.info("Getting settings...");
			response = ratingSettingsService.getRatingSettings();
			if (response.getRatingSettings() != null) {
				log.info("Settings found");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.warn("Settings not found");
				return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("An error has occurred!");
			response.setMessage("An error has occurred!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * API endpoint - updates rating settings.
	 *
	 * @param request request containing new settings.
	 * @return ResponseEntity with a HttpStatus code.
	 */
	@PutMapping("/settings")
	public ResponseEntity<String> updateRatingSettings(
		@Valid @RequestBody RatingSettingsRequest request) {
		try {
			log.info("Updating settings...");
			boolean isUpdated = ratingSettingsService.updateRatingSettings(request);
			if (isUpdated) {
				log.info("Settings successfully updated!");
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				log.warn("Problem occurred while updating settings");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("An error has occurred!");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * API endpoint - creates new rating.
	 *
	 * @param request object of the rating.
	 * @return ResponseEntity with RatingResponse and a HttpStatus code.
	 */
	@PostMapping
	public ResponseEntity<RatingResponse> createRating(@Valid @RequestBody RatingRequest request) {
		RatingResponse response = new RatingResponse();
		try {
			log.info("Creating rating...");
			response = ratingService.createRating(request);
			if (response.getRating() != null) {
				log.info("Rating created!");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			} else {
				log.warn("Problem occurred while creating rating");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			log.error("An error has occurred!");
			response.setMessage(HttpStatus.BAD_REQUEST.toString());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * API endpoint - gets all ratings between two dates.
	 *
	 * @param request contains two dates.
	 * @return a RatingBetweenDatesResponse with all the rating between two dates.
	 */
	@PostMapping("/statistics")
	public ResponseEntity<RatingsBetweenDatesResponse> getRatingsBetweenDates(
		@Valid @RequestBody RatingsBetweenDatesRequest request) {
		RatingsBetweenDatesResponse response = new RatingsBetweenDatesResponse();
		try {
			if (ratingService.areDatesValid(request.getStartDate(), request.getEndDate())) {
				// if difference between requested dates is 31 days
				log.info("Dates valid. Finding ratings...");
				response = ratingService.getRatingsBetweenDates(request);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				log.warn("Dates not valid!");
				response.setMessage(HttpStatus.BAD_REQUEST
					+ "! Difference between dates must be <= 31 days and start date must be before end date!");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (IllegalArgumentException e) {
			log.error("An error has occurred! Illegal Argument!");
			response.setMessage(HttpStatus.BAD_REQUEST.toString());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
}

