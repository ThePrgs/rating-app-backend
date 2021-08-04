package com.nsoft.ratingappbackend.emoji;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Emoji class represents an entity. It is used for rating classification. Every possible way
 * (emojis, stars, etc.) to rate something with, is stored in emoji table.
 *
 * @see com.nsoft.ratingappbackend.emojiconfig.EmojiConfig
 * @see com.nsoft.ratingappbackend.ratingsettings.RatingSettings
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "emoji")
public class Emoji {

	/**
	 * id is used to identify an emoji. It is unique.
	 */
	@Id
	@Column(name = "id")
	private Long id;

	/**
	 * name describes a rating classification (an emoji).
	 */
	@Column(name = "name")
	private String name;

	/**
	 * color is used to set our emoji a color that our SPA will use.
	 */
	@Column(name = "color")
	private String color;

	/**
	 * image is a remote link of an emoji image.
	 */
	@Column(name = "image")
	private String image;

}
