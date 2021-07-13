package com.nsoft.ratingappbackend.ratingsettings;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RatingSettingsResponse {

    @Min(value = 3, message = "Number of emoticons can't be below 3!")
    @Max(value = 5, message = "Number of emoticons can't be above 5!")
    private int numOfEmoticons;

    @Min(value = 0, message = "Timeout can't be a negative value!")
    @Max(value = 10, message = "Timeout can't be above 10!")
    private int timeout;

    @Size(min = 3, max = 120, message = "Message can be between 3 and 120 characters long.")
    private String msg;

}
