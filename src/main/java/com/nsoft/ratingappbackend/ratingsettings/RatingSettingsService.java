package com.nsoft.ratingappbackend.ratingsettings;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RatingSettingsService {

  private final RatingSettingsRepository ratingSettingsRepository;

  public RatingSettingsResponse getRatingSettings() {

    Optional<RatingSettings> ratingSettings = ratingSettingsRepository.findById(1L);
    if (ratingSettings.isPresent()) {

      return new RatingSettingsResponse(
          ratingSettings.get().getNumOfEmoticons(),
          ratingSettings.get().getTimeout(),
          ratingSettings.get().getMsg());
    } else {
      throw new NoSuchElementException();
    }
  }

  public boolean updateRatingSettings(RatingSettingsResponse request) {

    Optional<RatingSettings> obj = ratingSettingsRepository.findById(1L);
    if (obj.isPresent()) {
      obj.get().setNumOfEmoticons(request.getNumOfEmoticons());
      obj.get().setTimeout(request.getTimeout());
      obj.get().setMsg(request.getMsg());

      ratingSettingsRepository.save(obj.get());
      return true;
    } else {
      throw new NoSuchElementException();
    }
  }
}
