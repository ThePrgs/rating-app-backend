package com.nsoft.ratingappbackend.ratingsettings;

import java.util.Optional;
import com.nsoft.ratingappbackend.ratingsettings.payload.RatingSettingsRequest;
import com.nsoft.ratingappbackend.ratingsettings.payload.RatingSettingsResponse;
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
		RatingSettingsResponse ratingSettingsResponse = new RatingSettingsResponse();
		if(ratingSettings.isPresent()) {

			ratingSettingsResponse.setMessage("Rating settings found.");
			ratingSettingsResponse.setRatingSettings(
				new RatingSettings(
				ratingSettings.get().getNumOfEmoticons(),
				ratingSettings.get().getTimeout(),
				ratingSettings.get().getMsg()
			));
			return ratingSettingsResponse;
		} else {
			ratingSettingsResponse.setMessage("No rating settings found.");
			return ratingSettingsResponse;
		}
	}

	/**
	 * Method updates settings set in the repository
	 *
	 * @param request request containing new settings
	 * @return boolean
	 */
	public boolean updateRatingSettings(RatingSettingsRequest request) {

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
