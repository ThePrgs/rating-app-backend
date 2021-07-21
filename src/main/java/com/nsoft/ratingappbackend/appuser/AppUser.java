package com.nsoft.ratingappbackend.appuser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.Collections;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Entity for the user of the application
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser{

	@SequenceGenerator(
		name = "app_user_sequence",
		sequenceName = "app_user_sequence",
		allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_sequence")
	@Column(name = "id")
	private Long id;

	@Column(name = "name",nullable = false)
	private String name;

	@Email
	@Column(name = "email",nullable = false)
	private String email;

	@Column(name="image_url")
	private String imageUrl;

	@JsonIgnore
	private String password;

	@Column(name = "app_user_role")
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;

	@NotNull
	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	private String providerId;

}
