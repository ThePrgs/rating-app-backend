package com.nsoft.ratingappbackend.emoji;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmojiRepository extends JpaRepository<Emoji, Long> {

	List<Emoji> findAllByIdIn(List<Long> listEmoji);
}
