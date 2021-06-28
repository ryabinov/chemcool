package com.chemcool.school.lesson.storage.fixedanswer;

import com.chemcool.school.lesson.domain.fixedanswer.ChemFixedAnswerTask;
import com.chemcool.school.lesson.domain.fixedanswer.ChemFixedAnswerTaskEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChemFixedAnswerTaskEventJournal extends JpaRepository<ChemFixedAnswerTaskEvent, String>, JpaSpecificationExecutor<ChemFixedAnswerTask> {
}
