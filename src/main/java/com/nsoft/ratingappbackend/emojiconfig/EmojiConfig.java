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
 * Entity for emoji configuration
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "emoji_config")
@Entity
public class EmojiConfig {

	/**
	 * Column id is the primary key of the emoji config table
	 */
	@Id
	@Column(name = "id")
	private Long id;

	/**
	 * Column numOfEmoticons is the number of emoticons set in the settings table
	 */
	@Column(name = "num_of_emoticons")
	private Integer numOfEmoticons;

	/**
	 * Column emoji_id is a foreign key to the table emoji
	 */
	@ManyToOne
	@JoinColumn(name = "emoji_id")
	private Emoji emojiId;
}
