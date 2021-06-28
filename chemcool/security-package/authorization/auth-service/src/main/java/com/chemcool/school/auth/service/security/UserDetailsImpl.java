package com.chemcool.school.auth.service.security;

import com.chemcool.school.auth.service.domain.RegisterUser;
import com.chemcool.school.auth.service.domain.RegisterUserAccountRole;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Data
public class UserDetailsImpl implements OAuth2User, UserDetails {

    private String id;
    private String email;
    private String password;
    private boolean isEnabled;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;


    public UserDetailsImpl(String id, String email, String password, boolean isEnabled, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.isEnabled = isEnabled;
    }

    public static UserDetailsImpl create(RegisterUser user) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(RegisterUserAccountRole.ROLE_USER_BASE);

        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(),
                authorities
        );
    }

    public static UserDetailsImpl create(RegisterUser user, Map<String, Object> attributes) {
        UserDetailsImpl userDetailsImpl = UserDetailsImpl.create(user);
        userDetailsImpl.setAttributes(attributes);
        return userDetailsImpl;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return String.valueOf(id);
    }
}

