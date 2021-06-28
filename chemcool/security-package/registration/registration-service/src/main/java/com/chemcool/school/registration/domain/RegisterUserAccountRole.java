package com.chemcool.school.registration.domain;

import org.springframework.security.core.GrantedAuthority;

public enum RegisterUserAccountRole implements GrantedAuthority {
    ROLE_USER_BASE("ROLE_USER_BASE"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_TEACHER("ROLE_TEACHER"),
    ROLE_STUDENT("ROLE_STUDENT");


    RegisterUserAccountRole(String role) {
        this.role = role;
    }

    String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
