package com.nsoft.ratingappbackend.appuser;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleResponse {

	private String status;

	@Enumerated(EnumType.STRING)
	private AppUserRole role;
}