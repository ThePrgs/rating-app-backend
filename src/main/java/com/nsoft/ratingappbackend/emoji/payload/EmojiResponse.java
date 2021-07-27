package com.nsoft.ratingappbackend.emoji.payload;

import com.nsoft.ratingappbackend.emoji.Emoji;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class EmojiResponse {
	private String message;
	private List<Emoji> emojiList;
}
