package com.nsoft.ratingappbackend.ratingsettings;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository of the rating settings
 */
public interface RatingSettingsRepository extends JpaRepository<RatingSettings, Long> {

}
