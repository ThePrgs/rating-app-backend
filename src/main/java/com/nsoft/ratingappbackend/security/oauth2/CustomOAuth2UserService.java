package com.nsoft.ratingappbackend.security.oauth2;

import com.nsoft.ratingappbackend.appuser.AppUser;
import com.nsoft.ratingappbackend.appuser.AppUserRepository;
import com.nsoft.ratingappbackend.appuser.AuthProvider;
import com.nsoft.ratingappbackend.exceptions.OAuth2AuthenticationProcessingException;
import com.nsoft.ratingappbackend.security.UserPrincipal;
import com.nsoft.ratingappbackend.security.oauth2.user.OAuth2UserInfo;
import com.nsoft.ratingappbackend.security.oauth2.user.OAuth2UserInfoFactory;
import java.util.Optional;
import javax.naming.AuthenticationException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	private final AppUserRepository appUserRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest){
		OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
		try {
			return processOAuth2User(oAuth2UserRequest, oAuth2User);
		} catch (Exception ex) {
			// Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
			throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
		}
	}


	private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory
			.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
		if(ObjectUtils.isEmpty(oAuth2UserInfo.getEmail())) {
			throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
		}

		Optional<AppUser> userOptional = appUserRepository.findByEmail(oAuth2UserInfo.getEmail());
		AppUser user;
		if(userOptional.isPresent()) {
			user = userOptional.get();
			if(!user.getProvider().equals(
				AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
				throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
					user.getProvider() + " account. Please use your " + user.getProvider() +
					" account to login.");
			}
			user = updateExistingUser(user, oAuth2UserInfo);
		} else {
			user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
		}

		return UserPrincipal.create(user, oAuth2User.getAttributes());
	}
	private AppUser registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
		AppUser user = new AppUser();

		user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
		user.setProviderId(oAuth2UserInfo.getId());
		user.setName(oAuth2UserInfo.getName());
		user.setEmail(oAuth2UserInfo.getEmail());
		user.setImageUrl(oAuth2UserInfo.getImageUrl());
		return appUserRepository.save(user);
	}

	private AppUser updateExistingUser(AppUser existingUser, OAuth2UserInfo oAuth2UserInfo) {
		existingUser.setName(oAuth2UserInfo.getName());
		existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
		return appUserRepository.save(existingUser);
	}
}
