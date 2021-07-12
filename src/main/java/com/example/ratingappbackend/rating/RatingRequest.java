package com.example.ratingappbackend.rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RatingRequest {

    @Enumerated(EnumType.STRING)
    private String type;
}
