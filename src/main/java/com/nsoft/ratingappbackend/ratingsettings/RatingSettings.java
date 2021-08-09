package com.nsoft.ratingappbackend.ratingsettings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * RatingSettings class represents an entity. It is used to set the settings of our SPA´s public
 * view. With RatingSetting we define how many emojis (numOfEmoticons∈[3,5]) a user can see and what
 * message will be presented after rating. The feedback message will be shown after x seconds of
 * timeout where x∈[0,10].
 *
 * @see com.nsoft.ratingappbackend.emoji.Emoji
 * @see com.nsoft.ratingappbackend.emojiconfig.EmojiConfig
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rating_settings")
public class RatingSettings {

	/**
	 * id is used to identify the setting.
	 */
	@Id
	@Column(name = "id")
	private Long id = 1L;

	/**
	 * numOfEmoticons is a number of how many emojis are currently displayed.
	 */
	@Min(value = 3, message = "Number of emoticons can't be below 3!")
	@Max(value = 5, message = "Number of emoticons can't be above 5!")
	@Column(name = "num_of_emoticons", nullable = false, columnDefinition = "integer default 3")
	@NotNull
	private Integer numOfEmoticons = 3;

	/**
	 * timeout is a number of seconds after which the feedback message is shown.
	 */
	@Min(value = 0, message = "Timeout can't be a negative value!")
	@Max(value = 10, message = "Timeout can't be above 10!")
	@Column(name = "timeout", nullable = false, columnDefinition = "integer default 5")
	@NotNull
	private Integer timeout = 5;

	/**
	 * msg is a feedback message which is show to user after rating.
	 */
	@Size(min = 3, max = 120, message = "Message can be between 3 and 120 characters.")
	@Column(name = "msg", columnDefinition = "varchar(255) default 'Thank you for rating.'")
	private String msg = "Thank you for your rating";

	public RatingSettings(Integer numOfEmoticons, Integer timeout, String msg) {
		this.numOfEmoticons = numOfEmoticons;
		this.timeout = timeout;
		this.msg = msg;
	}

}
