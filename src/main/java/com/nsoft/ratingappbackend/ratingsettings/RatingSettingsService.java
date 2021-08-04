package com.nsoft.ratingappbackend.ratingsettings;

import com.nsoft.ratingappbackend.ratingsettings.payload.RatingSettingsRequest;
import com.nsoft.ratingappbackend.ratingsettings.payload.RatingSettingsResponse;
import com.pusher.rest.Pusher;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for RatingSettings.
 *
 * @see RatingSettings
 */
@Service
@AllArgsConstructor
public class RatingSettingsService {

	private final RatingSettingsRepository ratingSettingsRepository;

	/**
	 * Method gets the current rating settings.
	 *
	 * @return RatingSettingResponse with a message and current rating settings.
	 */
	public RatingSettingsResponse getRatingSettings() {

		Optional<RatingSettings> ratingSettings = ratingSettingsRepository.findById(1L);
		RatingSettingsResponse ratingSettingsResponse = new RatingSettingsResponse();
		if (ratingSettings.isPresent()) {

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
	 * Method updates rating settings.
	 *
	 * @param request request containing new settings.
	 * @return a boolean value whether the settings were updated or not.
	 */

	public boolean updateRatingSettings(RatingSettingsRequest request) {
		Pusher pusher = new Pusher("1233197", "f47f2ad6b875f07ee437", "f7193bdaff0fcff4990a");
		pusher.setCluster("eu");
		pusher.setEncrypted(true);

		Optional<RatingSettings> obj = ratingSettingsRepository.findById(1L);
		if (obj.isPresent()) {
			obj.get().setNumOfEmoticons(request.getNumOfEmoticons());
			obj.get().setTimeout(request.getTimeout());
			obj.get().setMsg(request.getMsg());

			ratingSettingsRepository.save(obj.get());
			pusher.trigger("settings", "settings-updated", obj);
			return true;
		} else {
			return false;
		}
	}
}
