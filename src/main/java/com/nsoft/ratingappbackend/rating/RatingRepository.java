package com.nsoft.ratingappbackend.rating;

import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Rating entity repository
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

	List<Rating> findAllByDateBetween(Instant firstDate, Instant endDate);
}
