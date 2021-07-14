package com.nsoft.ratingappbackend.emojiconfig;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmojiConfigRepository extends JpaRepository<EmojiConfig, Long> {
  List<EmojiConfig> findByNumOfEmoticons(Integer numOfEmoticons);
}
