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
        if(ratingSettings.isPresent()){

            return new RatingSettingsResponse(
                    ratingSettings.get().getNumOfEmoticons(),
                    ratingSettings.get().getTimeout(),
                    ratingSettings.get().getMsg()
            );
        } else {
            throw new NoSuchElementException();
        }
    }
}
