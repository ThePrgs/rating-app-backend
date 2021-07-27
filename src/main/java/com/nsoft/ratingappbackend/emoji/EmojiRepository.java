package com.nsoft.ratingappbackend.emoji;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for emoji table
 */
@Repository
public interface EmojiRepository extends JpaRepository<Emoji, Long> {

	/**
	 * JPQL that finds emojis by id
	 *
	 * @param listEmoji list containing emoji ids
	 * @return List
	 */
	List<Emoji> findAllByIdIn(List<Long> listEmoji);
}
