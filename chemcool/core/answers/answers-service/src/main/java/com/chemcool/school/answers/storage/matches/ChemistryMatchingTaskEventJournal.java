package com.chemcool.school.answers.storage.matches;

import com.chemcool.school.answers.domain.matches.ChemistryMatchingTaskEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemistryMatchingTaskEventJournal extends JpaRepository<ChemistryMatchingTaskEvent, String> {
}
