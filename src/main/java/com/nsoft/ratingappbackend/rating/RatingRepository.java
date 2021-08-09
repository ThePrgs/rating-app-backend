package com.nsoft.ratingappbackend.rating;

import java.time.Instant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Rating.
 *
 * @see Rating
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {

	/**
	 * Query all ratings between two dates.
	 *
	 * @param firstDate starting point in time.
	 * @param endDate   ending point in time.
	 * @return a list with all ratings between two dates.
	 */
	List<Rating> findAllByDateBetween(Instant firstDate, Instant endDate);
}
