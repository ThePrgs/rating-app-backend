package com.nsoft.ratingappbackend.emoji;

import com.nsoft.ratingappbackend.emojiconfig.EmojiConfig;
import com.nsoft.ratingappbackend.emojiconfig.EmojiConfigService;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service class for emojis
 */
@Service
@AllArgsConstructor
public class EmojiService {

	private final EmojiRepository emojiRepository;
	private final EmojiConfigService emojiConfigService;

	/**
	 * Method returns list of emojis that are used in current configuration
	 * @return List
	 */
	public List<Emoji> getEmojis() {

		List<EmojiConfig> emojiConfigList = emojiConfigService.getEmojisConfig();

		if (!emojiConfigList.isEmpty()) {
			List<Long> emojiIDs = new ArrayList<>();
			for (EmojiConfig ec : emojiConfigList) {
				emojiIDs.add(ec.getEmojiId().getId());
			}
			return emojiRepository.findAllByIdIn(emojiIDs);
		}

		return new ArrayList<>();
	}
}
