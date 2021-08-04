package com.nsoft.ratingappbackend.appuser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * AppUser class represents an entity. It is used for storing the users of our application in the
 * database. Every user contains a role that is added to Spring Security´s SimpleGrantedAuthority.
 *
 * @see AppUserRole
 * @see SimpleGrantedAuthority
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "app_user")
public class AppUser implements UserDetails {

	/**
	 * id is used as a sequence number.
	 */
	@SequenceGenerator(
		name = "app_user_sequence",
		sequenceName = "app_user_sequence",
		allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_user_sequence")
	@Column(name = "id")
	private Long id;

	/**
	 * email is used to identify a user. It is unique.
	 */
	@Email
	@Column(name = "email", nullable = false, unique = true)
	private String email;


	/**
	 * appUserRole is an identifier od user authorities and permissions.
	 */
	@Column(name = "app_user_role")
	@Enumerated(EnumType.STRING)
	private AppUserRole appUserRole;

	public AppUser(String email, AppUserRole appUserRole) {
		this.email = email;
		this.appUserRole = appUserRole;
	}

	/**
	 * Method gets all user authorities.
	 *
	 * @return SimpleGrantedAuthority.
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + this.getAppUserRole()));

		return authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
