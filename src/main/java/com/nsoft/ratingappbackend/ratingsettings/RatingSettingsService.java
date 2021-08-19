package com.nsoft.ratingappbackend.ratingsettings;

import com.nsoft.ratingappbackend.emoji.EmojiService;
import com.nsoft.ratingappbackend.emoji.payload.EmojiResponse;
import com.nsoft.ratingappbackend.ratingsettings.payload.PusherResponse;
import com.nsoft.ratingappbackend.ratingsettings.payload.RatingSettingsRequest;
import com.nsoft.ratingappbackend.ratingsettings.payload.RatingSettingsResponse;
import com.pusher.rest.Pusher;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service class for RatingSettings.
 *
 * @see RatingSettings
 */
@Service
@AllArgsConstructor
@Slf4j
public class RatingSettingsService {

	private final RatingSettingsRepository ratingSettingsRepository;
	private final EmojiService emojiService;
	private final Pusher pusher;

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
		PusherResponse pusherResponse = new PusherResponse();
		Optional<RatingSettings> obj = ratingSettingsRepository.findById(1L);
		if (obj.isPresent()) {
			obj.get().setNumOfEmoticons(request.getNumOfEmoticons());
			obj.get().setTimeout(request.getTimeout());
			obj.get().setMsg(request.getMsg());
			EmojiResponse emojiResponse = emojiService.getEmojis();
			ratingSettingsRepository.save(obj.get());
			if(obj.get().getMsg() == null){
				obj.get().setMsg("");
			}

			pusherResponse.setEmojiList(emojiResponse.getEmojiList());
			pusherResponse.setRatingSettings(obj.get());

			log.info("Sending update to pusher!");
			pusher.trigger("settings", "settings-updated", pusherResponse);
			return true;
		} else {
			return false;
		}
	}
}
