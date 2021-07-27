package com.nsoft.ratingappbackend.rating;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nsoft.ratingappbackend.emoji.Emoji;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Entity for rating table
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Table(name = "rating")
public class Rating {

	/**
	 * Column id is the primary key of the ratings table
	 */
	@SequenceGenerator(
		name = "rating_sequence",
		sequenceName = "rating_sequence",
		allocationSize = 1)
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_sequence")
	@Column(name = "id")
	private Long id;

	/**
	 * Column emoji_id is a foreign key on the table emoji
	 */
	@ManyToOne
	@JoinColumn(name = "emoji_id")
	private Emoji emojiId;

	/**
	 * Column date is the date when the emoji was saved
	 */
	@Column(name = "date")
	@NotNull
	private Instant date;

	public Rating(Emoji emojiId, Instant date) {

		this.emojiId = emojiId;
		this.date = date;
	}
}
