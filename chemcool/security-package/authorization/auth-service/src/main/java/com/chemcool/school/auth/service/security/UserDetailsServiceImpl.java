package com.chemcool.school.auth.service.security;

import com.chemcool.school.auth.service.domain.RegisterUser;
import com.chemcool.school.auth.service.exeption.ResourceNotFoundException;
import com.chemcool.school.auth.service.storage.RegisterUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    RegisterUserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        RegisterUser user = repository.findByEmail(email)
                 .orElseThrow(() ->
                        new UsernameNotFoundException("Не найден пользователь с Email : " + email)
                );

        return UserDetailsImpl.create(user);
    }

    @Transactional
    public UserDetails loadUserById(String id) {
        RegisterUser user = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserDetailsImpl.create(user);
    }
}
