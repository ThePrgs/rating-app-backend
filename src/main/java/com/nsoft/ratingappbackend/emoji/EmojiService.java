package com.nsoft.ratingappbackend.emoji;

import com.nsoft.ratingappbackend.ratingsettings.RatingSettings;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettingsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmojiService {
    private final EmojiRepository emojiRepository;
    private final RatingSettingsRepository ratingSettingsRepository;

    public List<Emoji> getEmojis(){
        Optional<RatingSettings> ratingSettings = ratingSettingsRepository.findById(1L);
        List<Emoji> emojis = new ArrayList<>();
        Optional<Emoji> veryHappy = emojiRepository.findById(5L);
        Optional<Emoji> happy = emojiRepository.findById(4L);
        Optional<Emoji> meh = emojiRepository.findById(3L);
        Optional<Emoji> sad = emojiRepository.findById(2L);
        Optional<Emoji> verySad = emojiRepository.findById(1L);
        int numOfEmojis = ratingSettings.get().getNumOfEmoticons();
        switch (numOfEmojis){
            case 3:
                emojis.add(veryHappy.get());
                emojis.add(meh.get());
                emojis.add(verySad.get());
                break;

            case 4:
                emojis.add(veryHappy.get());
                emojis.add(happy.get());
                emojis.add(meh.get());
                emojis.add(verySad.get());
                break;

            case 5:
                emojis.add(veryHappy.get());
                emojis.add(happy.get());
                emojis.add(meh.get());
                emojis.add(sad.get());
                emojis.add(verySad.get());
                break;
        }
        return emojis;
    }


}
