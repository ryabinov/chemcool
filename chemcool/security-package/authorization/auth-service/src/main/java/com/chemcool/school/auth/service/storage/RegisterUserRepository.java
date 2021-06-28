package com.chemcool.school.auth.service.storage;

import com.chemcool.school.auth.service.domain.RegisterUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisterUserRepository extends JpaRepository<RegisterUser, String> {
    Optional<RegisterUser> findByEmail(String userEmail);

}
