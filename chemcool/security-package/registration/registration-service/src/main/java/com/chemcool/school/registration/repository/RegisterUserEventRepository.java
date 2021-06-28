package com.chemcool.school.registration.repository;

import com.chemcool.school.registration.domain.RegisterUserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterUserEventRepository extends JpaRepository<RegisterUserEvent, String> {
}
