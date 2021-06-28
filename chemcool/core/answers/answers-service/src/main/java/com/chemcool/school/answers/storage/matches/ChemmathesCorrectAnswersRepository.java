package com.chemcool.school.answers.storage.matches;

import com.chemcool.school.answers.domain.matches.ChemmathesCorrectAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemmathesCorrectAnswersRepository extends JpaRepository<ChemmathesCorrectAnswers, String> {
}
