package com.nsoft.ratingappbackend.emoji;

import com.nsoft.ratingappbackend.emojiconfig.EmojiConfig;
import com.nsoft.ratingappbackend.emojiconfig.EmojiConfigService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class EmojiService {

    private final EmojiRepository emojiRepository;
    private final EmojiConfigService emojiConfigService;

    public List<Emoji> getEmojis() {
        List<EmojiConfig> emojiConfigList = emojiConfigService.getEmojisConfig();
        if(emojiConfigList.isEmpty()){
            throw new NoSuchElementException();
        }
        List<Long> emojiIDs = new ArrayList<>();
        for(EmojiConfig ec:emojiConfigList){
            emojiIDs.add(ec.getEmojiId().getId());
        }
        return emojiRepository.findAllByIdIn(emojiIDs);

    }
}
