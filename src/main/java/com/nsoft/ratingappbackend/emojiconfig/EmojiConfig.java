package com.nsoft.ratingappbackend.emojiconfig;


import com.nsoft.ratingappbackend.emoji.Emoji;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "emoji_config")
@Entity
public class EmojiConfig {

    @Id
    @Column(name="id")
    private Long id;


    @Column(name="num_of_emoticons")
    private Integer numOfEmoticons;

    @ManyToOne
    @JoinColumn(name="emoji_id")
    private Emoji emojiId;

}
