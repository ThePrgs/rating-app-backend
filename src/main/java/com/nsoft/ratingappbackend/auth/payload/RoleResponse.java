package com.nsoft.ratingappbackend.auth.payload;


import com.nsoft.ratingappbackend.appuser.AppUserRole;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Client receives this payload after the access token integrity check.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleResponse {

	private String status;

	@Enumerated(EnumType.STRING)
	private AppUserRole role;
}
