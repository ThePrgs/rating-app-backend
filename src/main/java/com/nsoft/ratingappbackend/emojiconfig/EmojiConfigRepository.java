package com.nsoft.ratingappbackend.emojiconfig;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for EmojiConfig.
 *
 * @see EmojiConfig
 */
@Repository
public interface EmojiConfigRepository extends JpaRepository<EmojiConfig, Long> {

	/**
	 * Methods returns an EmojiConfig list using number of emojis.
	 *
	 * @param numOfEmoticons number of emoticons set in settings.
	 * @return a list of EmojiConfig.
	 */
	List<EmojiConfig> findByNumOfEmoticons(Integer numOfEmoticons);
}
