package com.nsoft.ratingappbackend.rating;




import com.nsoft.ratingappbackend.emoji.Emoji;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Table(name="rating")
public class Rating {

    @SequenceGenerator(
            name = "rating_sequence",
            sequenceName = "rating_sequence",
            allocationSize = 1
    )

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "rating_sequence"
    )
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="emoji_id")
    private Emoji emojiId;

    @Column(name="date")
    private LocalDateTime date;

    public Rating(Emoji emojiId, LocalDateTime date){

        this.emojiId = emojiId;
        this.date=date;
    }
}
