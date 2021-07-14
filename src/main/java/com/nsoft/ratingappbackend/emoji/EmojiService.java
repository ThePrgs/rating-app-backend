package com.nsoft.ratingappbackend.emoji;

import com.nsoft.ratingappbackend.ratingsettings.RatingSettings;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmojiService {

    private final EmojiRepository emojiRepository;
    private final RatingSettingsRepository ratingSettingsRepository;

    public List<Emoji> getEmojis() {

        Optional<RatingSettings> ratingSettings = ratingSettingsRepository.findById(1L);

        List<Emoji> emojiList = emojiRepository.findAll();

        int numOfEmojis = ratingSettings.get().getNumOfEmoticons();

        switch (numOfEmojis){

            case 3:
                emojiList.remove(1);
                emojiList.remove(2);
                break;
            case 4:
                emojiList.remove(1);
                break;
        }

        return emojiList;
    }
}
