package com.chemcool.school.auth.service.domain;

import org.springframework.security.core.GrantedAuthority;

public enum RegisterUserAccountRole implements GrantedAuthority {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER_PREMIUM("ROLE_USER_PREMIUM"),
    ROLE_USER_BASE("ROLE_USER_BASE");

    private String role;

    RegisterUserAccountRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
