package com.chemcool.school.tasks.infrastructure.storage.chemmatches;

import com.chemcool.school.tasks.domain.chemmatches.ChemistryMatchingTaskEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemistryMatchingTaskEventJournal extends JpaRepository<ChemistryMatchingTaskEvent, String> {
}
