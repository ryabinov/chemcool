package com.chemcool.school.lesson.storage.matches;

import com.chemcool.school.lesson.domain.matches.ChemMatchingTask;
import com.chemcool.school.lesson.domain.matches.ChemMatchingTaskEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemMatchingTaskEventJournal extends JpaRepository<ChemMatchingTaskEvent, String>, JpaSpecificationExecutor<ChemMatchingTask> {
}
