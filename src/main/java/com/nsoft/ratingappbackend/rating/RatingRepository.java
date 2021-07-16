package com.nsoft.ratingappbackend.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Rating entity repository
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

}
