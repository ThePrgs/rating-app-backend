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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Rating class represents an entity. Ratings are simple evaluations stored in database with an
 * emoji (score) and datetime. It is an integral part of the application because we use if for
 * further development of statistics.
 *
 * @see Emoji
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "rating")
public class Rating {

	/**
	 * id is an identifier.
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
	 * emojiId is a foreign key that connects to Emoji table (many-to-one relationship).
	 */
	@ManyToOne
	@JoinColumn(name = "emoji_id")
	private Emoji emojiId;

	/**
	 * date represents date and time when the emoji was stored.
	 */
	@Column(name = "date")
	@NotNull
	private Instant date;

	public Rating(Emoji emojiId, Instant date) {

		this.emojiId = emojiId;
		this.date = date;
	}

	/**
	 * Emoji getter.
	 *
	 * @return Id of an emoji instead of whole object.
	 */
	public Long getEmojiId() {
		return emojiId.getId();
	}
}
