package com.example.ratingappbackend.rating;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RatingService {

    public final RatingRepository ratingRepository;

    public ResponseEntity<RatingRequest> create(RatingRequest request){

        for (RatingEnum item:RatingEnum.values()) {
            if(item.toString().equals(request.getType().toUpperCase())){
                Rating rating = new Rating(
                        item,
                        LocalDateTime.now()
                );
                ratingRepository.save(rating);
                return new ResponseEntity<>(request, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(request,HttpStatus.BAD_REQUEST);
    }
}
