package com.chemcool.school.auth.service.security.oauth2;

import com.chemcool.school.auth.service.domain.*;
import com.chemcool.school.auth.service.exeption.OAuth2AuthenticationProcessingException;
import com.chemcool.school.auth.service.security.UserDetailsImpl;
import com.chemcool.school.auth.service.security.oauth2.userInfo.OAuth2UserInfo;
import com.chemcool.school.auth.service.security.oauth2.userInfo.OAuth2UserInfoFactory;
import com.chemcool.school.auth.service.service.RegisterUserEventNotificationService;
import com.chemcool.school.auth.service.service.RegisterUserProxyService;
import com.chemcool.school.auth.service.storage.RegisterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.UUID;

/**
 * запрашивается информация о пользователе. сохраняет пользователя в БД.
 */

@Service
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private RegisterUserRepository userRepository;
    @Autowired
    private RegisterUserProxyService registerUserProxyService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if (StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email не найден");
        }

        Optional<RegisterUser> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        RegisterUser user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            if (!user.getProvider().equals(RegisterUserAuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Похоже, вы уже зарегистрировались через " +
                        user.getProvider() + " аккаунт. Используйте" + user.getProvider() +
                        " аккаунт чтобы войти.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
        } else {
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserDetailsImpl.create(user, oAuth2User.getAttributes());
    }

    private RegisterUser registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        RegisterUser user = new RegisterUser();
        user.setId(UUID.randomUUID().toString());
        user.setProvider(RegisterUserAuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setName(oAuth2UserInfo.getFirstName());
        user.setSurname(oAuth2UserInfo.getLastName());
        user.setEmail(oAuth2UserInfo.getEmail());
        user.setImageUrl(oAuth2UserInfo.getImageUrl());
        user.setRole(RegisterUserAccountRole.ROLE_USER_BASE);
        user.setType(RegisterUserAccountType.BASE);
        user.setEnabled(true);

        return registerUserProxyService.add(user);
    }

    private RegisterUser updateExistingUser(RegisterUser existingUser, OAuth2UserInfo oAuth2UserInfo) {
        existingUser.setName(oAuth2UserInfo.getFirstName());
        existingUser.setSurname(oAuth2UserInfo.getLastName());
        existingUser.setImageUrl(oAuth2UserInfo.getImageUrl());
        return registerUserProxyService.add(existingUser);
    }
}
