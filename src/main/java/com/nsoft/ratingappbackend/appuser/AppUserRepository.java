package com.nsoft.ratingappbackend.appuser;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for the user of the application
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	/**
	 * Method compares emails in order to see if the user exists
	 *
	 * @param email email of the user
	 * @return AppUser
	 */
	Optional<AppUser> findByEmail(String email);
}
