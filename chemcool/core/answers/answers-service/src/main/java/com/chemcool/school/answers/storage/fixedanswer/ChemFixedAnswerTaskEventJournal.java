package com.chemcool.school.answers.storage.fixedanswer;

import com.chemcool.school.answers.domain.fixedanswer.ChemFixedAnswerTaskEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemFixedAnswerTaskEventJournal extends JpaRepository<ChemFixedAnswerTaskEvent, String> {
}
