package com.nsoft.ratingappbackend.emojiconfig;

import com.nsoft.ratingappbackend.emoji.Emoji;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * EmojiConfig class represents an entity. It is used to store all possible (numOfEmoticons∈[3,5])
 * variations to present rating settings. It defines which emojis will be show with currently set
 * RatingSettings´ numOfEmoticons.
 * <p>
 * Example: If numOfEmoticons is set to 3, we display Emojis with id 1, 3 and 5.
 *
 * @see Emoji
 * @see com.nsoft.ratingappbackend.ratingsettings.RatingSettings
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "emoji_config")
@Entity
public class EmojiConfig {

	/**
	 * id is a sequence number.
	 */
	@Id
	@Column(name = "id")
	private Long id;

	/**
	 * numOfEmoticons is a number of emojis that can possibly be shown.
	 */
	@Column(name = "num_of_emoticons")
	private Integer numOfEmoticons;

	/**
	 * emojiId is used to set which emoji will be show with its corresponding numOfEmoticons.
	 */
	@ManyToOne
	@JoinColumn(name = "emoji_id")
	private Emoji emojiId;
}
