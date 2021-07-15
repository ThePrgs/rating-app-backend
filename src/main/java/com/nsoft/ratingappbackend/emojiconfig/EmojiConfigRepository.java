package com.nsoft.ratingappbackend.emojiconfig;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmojiConfigRepository extends JpaRepository<EmojiConfig, Long> {

	List<EmojiConfig> findByNumOfEmoticons(Integer numOfEmoticons);
}
