package com.nsoft.ratingappbackend.ratingsettings;

import java.util.NoSuchElementException;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for the settings table
 */
@Service
@AllArgsConstructor
public class RatingSettingsService {

	private final RatingSettingsRepository ratingSettingsRepository;

	/**
	 * Method gets the settings from the repository
	 *
	 * @return RatingSettingResponse
	 */
	public RatingSettingsResponse getRatingSettings() {

		Optional<RatingSettings> ratingSettings = ratingSettingsRepository.findById(1L);
		if (ratingSettings.isPresent()) {

			return new RatingSettingsResponse(
				ratingSettings.get().getNumOfEmoticons(),
				ratingSettings.get().getTimeout(),
				ratingSettings.get().getMsg());
		} else {
			throw new NoSuchElementException();
		}
	}

	/**
	 * Method updates settings set in the repository
	 *
	 * @param request request containing new settings
	 * @return boolean
	 */
	public boolean updateRatingSettings(RatingSettingsResponse request) {

		Optional<RatingSettings> obj = ratingSettingsRepository.findById(1L);
		if (obj.isPresent()) {
			obj.get().setNumOfEmoticons(request.getNumOfEmoticons());
			obj.get().setTimeout(request.getTimeout());
			obj.get().setMsg(request.getMsg());

			ratingSettingsRepository.save(obj.get());
			return true;
		} else {
			return false;
		}
	}
}
