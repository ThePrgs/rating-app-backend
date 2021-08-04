package com.nsoft.ratingappbackend.emojiconfig;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.nsoft.ratingappbackend.emoji.Emoji;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettings;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettingsRepository;
import com.nsoft.ratingappbackend.ratingsettings.payload.RatingSettingsResponse;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class EmojiConfigServiceTest {

	EmojiConfigRepository emojiConfigRepository = mock(EmojiConfigRepository.class);
	RatingSettingsRepository ratingSettingsRepository = mock(RatingSettingsRepository.class);

	EmojiConfigService emojiConfigService = new EmojiConfigService(emojiConfigRepository,ratingSettingsRepository);

	@Test
	void getEmojisConfig() {
		List<RatingSettings> settings = Arrays.asList(
			new RatingSettings( 3, 3, "Test")
		);
		when(ratingSettingsRepository.findAll()).thenReturn(settings);
		Emoji emoji1Test = new Emoji(1L, "VERY_HAPPY", "rgb(0, 168, 107)", "https://res.cloudinary.com/dxlyytkww/image/upload/v1627541878/RatingApp/very_happy_ujm04f.svg");
		Emoji emoji3Test = new Emoji(3L, "MEH", "rgb(122, 122, 122)", "https://res.cloudinary.com/dxlyytkww/image/upload/v1627541877/RatingApp/meh_owc1iz.svg");
		Emoji emoji5Test = new Emoji(5L, "VERY_SAD", "rgb(249, 88, 90)", "https://res.cloudinary.com/dxlyytkww/image/upload/v1627541878/RatingApp/very_sad_rwkai6.svg");
		List<EmojiConfig> emojiConfigs = Arrays.asList(
			new EmojiConfig(1L, 3, emoji1Test),
			new EmojiConfig(2L, 3, emoji3Test),
			new EmojiConfig(3L, 3, emoji5Test)
		);
		when(emojiConfigRepository.findByNumOfEmoticons(settings.get(0).getNumOfEmoticons())).thenReturn(emojiConfigs);


		List<EmojiConfig> configs = emojiConfigService.getEmojisConfig();

		assertEquals(configs,emojiConfigs);


	}
}