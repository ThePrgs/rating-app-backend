package com.nsoft.ratingappbackend.ratingsettings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "rating_settings")
public class RatingSettings {

    @Id
    @Column(name = "id")
    private Long id = 1L;

    @Min(value = 3, message = "Number of emoticons can't be below 3!")
    @Max(value = 5, message = "Number of emoticons can't be above 5!")
    @Column(name = "num_of_emoticons")
    private int numOfEmoticons = 3;

    @Min(value = 0, message = "Timeout can't be a negative value!")
    @Max(value = 10, message = "Timeout can't be above 10!")
    @Column(name = "timeout")
    private int timeout = 5;

    @Size(min = 3, max = 120, message = "Thank you for rating.")
    @Column(name = "msg")
    private String msg = "Thank you for your rating";

    public RatingSettings(int numOfEmoticons, int timeout, String msg) {
            this.numOfEmoticons = numOfEmoticons;
            this.timeout = timeout;
            this.msg = msg;
    }
}
