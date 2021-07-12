package com.example.ratingappbackend.rating;




import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@EqualsAndHashCode
@NoArgsConstructor
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
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(name="type")
    private RatingEnum type;
    @Column(name="date")
    private LocalDateTime date;

    public Rating(RatingEnum type, LocalDateTime date){
        this.date=date;
        this.type=type;
    }
}
