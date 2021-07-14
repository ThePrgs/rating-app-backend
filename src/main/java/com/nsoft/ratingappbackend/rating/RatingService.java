package com.nsoft.ratingappbackend.rating;

import com.nsoft.ratingappbackend.emoji.Emoji;
import com.nsoft.ratingappbackend.emoji.EmojiRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingService {

  private final RatingRepository ratingRepository;
  private final EmojiRepository emojiRepository;

  public boolean createRating(RatingRequest request) {

    if (emojiRepository.findById(request.getEmojiId()).isPresent()) {

      Optional<Emoji> emoji = emojiRepository.findById(request.getEmojiId());
      Rating rating = new Rating(emoji.get(), LocalDateTime.now());

      ratingRepository.save(rating);
      return true;
    }

    return false;
  }
}
