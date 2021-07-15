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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "emoji_config")
@Entity
public class EmojiConfig {

	@Id
	@Column(name = "id")
	private Long id;

	@Column(name = "num_of_emoticons")
	private Integer numOfEmoticons;

	@ManyToOne
	@JoinColumn(name = "emoji_id")
	private Emoji emojiId;
}
