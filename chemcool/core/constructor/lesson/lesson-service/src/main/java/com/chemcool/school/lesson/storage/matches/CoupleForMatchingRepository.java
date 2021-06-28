package com.chemcool.school.lesson.storage.matches;

import com.chemcool.school.lesson.domain.matches.CoupleForMatching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoupleForMatchingRepository extends JpaRepository<CoupleForMatching, String> {
}