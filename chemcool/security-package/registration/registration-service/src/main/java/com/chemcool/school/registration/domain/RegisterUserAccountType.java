package com.chemcool.school.registration.domain;

import org.springframework.security.core.GrantedAuthority;

public enum RegisterUserAccountType implements GrantedAuthority {
    BASE("BASE"),
    PREMIUM("PREMIUM");

    private final String type;

    RegisterUserAccountType(String type) {
        this.type = type;
    }

    @Override
    public String getAuthority() {
        return type;
    }
}
