package com.example.ratingappbackend.rating;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RatingService {

    public final RatingRepository ratingRepository;

    public String create(RatingRequest request){

        for (RatingEnum item:RatingEnum.values()) {
            if(item.toString().equals(request.getType().toUpperCase())){
                Rating rating = new Rating(
                        item,
                        LocalDateTime.now()
                );
                ratingRepository.save(rating);
                return "Success";
            }
        }
        return "No such type";
    }
}
