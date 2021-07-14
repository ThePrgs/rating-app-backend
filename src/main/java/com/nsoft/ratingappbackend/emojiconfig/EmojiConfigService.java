package com.nsoft.ratingappbackend.emojiconfig;

import com.nsoft.ratingappbackend.ratingsettings.RatingSettings;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmojiConfigService {

    private final EmojiConfigRepository emojiConfigRepository;
    private final RatingSettingsRepository ratingSettingsRepository;

    public List<EmojiConfig> getEmojisConfig(){
        List<RatingSettings> ratingSettingsList = ratingSettingsRepository.findAll();
        int numOfEmoticons=ratingSettingsList.get(0).getNumOfEmoticons();



        return emojiConfigRepository.findByNumOfEmoticons(numOfEmoticons);

    }
}
