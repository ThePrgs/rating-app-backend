package com.nsoft.ratingappbackend.emojiconfig;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for storing emoji configs
 */
@Repository
public interface EmojiConfigRepository extends JpaRepository<EmojiConfig, Long> {

	/**
	 * Methods takes numOfEmoticons and with it using JPQL finds and returns EmojiConfig list
	 *
	 * @param numOfEmoticons number of emoticons set in settings
	 * @return List
	 */
	List<EmojiConfig> findByNumOfEmoticons(Integer numOfEmoticons);
}
