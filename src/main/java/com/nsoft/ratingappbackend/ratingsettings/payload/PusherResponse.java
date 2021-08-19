package com.nsoft.ratingappbackend.ratingsettings.payload;


import com.nsoft.ratingappbackend.emoji.Emoji;
import com.nsoft.ratingappbackend.ratingsettings.RatingSettings;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PusherResponse {

	List<Emoji> emojiList;
	RatingSettings ratingSettings;

}
