package com.chemcool.school.tasks.infrastructure.storage.chemfixedanswer;


import com.chemcool.school.tasks.domain.chemfixedanswer.ChemFixedAnswerTask;
import com.chemcool.school.tasks.domain.chemfixedanswer.ChemFixedAnswerTaskEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChemFixedAnswerTaskEventJournal extends JpaRepository<ChemFixedAnswerTaskEvent, String>, JpaSpecificationExecutor<ChemFixedAnswerTask> {
}
