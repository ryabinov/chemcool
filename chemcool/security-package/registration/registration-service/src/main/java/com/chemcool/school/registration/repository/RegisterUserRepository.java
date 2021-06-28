package com.chemcool.school.registration.repository;

import com.chemcool.school.registration.domain.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterUserRepository extends JpaRepository<RegisterUser, String> {

    RegisterUser findByEmail(String email);

    Boolean existsByEmail(String email);

    RegisterUser findByVerificationCode(String code);

    RegisterUser findByResetPasswordToken(String resetToken);
}