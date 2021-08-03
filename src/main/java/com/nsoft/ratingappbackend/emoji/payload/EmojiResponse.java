package com.nsoft.ratingappbackend.emoji.payload;

import com.nsoft.ratingappbackend.emoji.Emoji;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Client receives payload with list of emojis that are currently in use
 *
 * @see com.nsoft.ratingappbackend.emojiconfig.EmojiConfig
 */
@Getter
@Setter
@NoArgsConstructor
public class EmojiResponse {

	private String message;
	private List<Emoji> emojiList;
}
