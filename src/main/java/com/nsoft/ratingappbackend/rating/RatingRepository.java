package com.nsoft.ratingappbackend.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.Instant;
import java.util.List;

/**
 * Rating entity repository
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
	List<Rating> findAllByDateBetween(Instant firstDate, Instant endDate);
}
