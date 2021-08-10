package com.nsoft.ratingappbackend.emojiconfig;

import com.nsoft.ratingappbackend.ratingsettings.RatingSettings;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettingsRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service class for EmojiConfig.
 *
 * @see EmojiConfig
 */
@Service
@AllArgsConstructor
@Slf4j
public class EmojiConfigService {

	private final EmojiConfigRepository emojiConfigRepository;
	private final RatingSettingsRepository ratingSettingsRepository;

	/**
	 * Method returns List of EmojiConfigs filtered using numOfEmoticons.
	 *
	 * @return List
	 */
	public List<EmojiConfig> getEmojisConfig() {
		List<RatingSettings> ratingSettingsList = ratingSettingsRepository.findAll();
		int numOfEmoticons = ratingSettingsList.get(0).getNumOfEmoticons();
		log.info("Getting emoji configs");
		return emojiConfigRepository.findByNumOfEmoticons(numOfEmoticons);
	}
}
