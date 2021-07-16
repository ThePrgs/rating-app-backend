package com.nsoft.ratingappbackend.emoji;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Emoji entity class is a class for rating classification
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "emoji")
public class Emoji {

	/**
	 * Column id is the primary key of the emoji table
	 */
	@Id
	@Column(name = "id")
	private Long id;

	/**
	 * Column name is used for the name of an emoji
	 */
	@Column(name = "name")
	private String name;

	/**
	 * Column color is used to set the color of our emoji
	 * using RGB mapping
	 */
	@Column(name = "color")
	private String color;

	/**
	 * Column image is used to show the location of the used image
	 */
	@Column(name = "image")
	private String image;


	public Emoji(String name, String color, String image) {
		this.name = name;
		this.color = color;
		this.image = image;
	}
}
