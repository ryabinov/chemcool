package com.chemcool.school.tasks.infrastructure.storage.chemsingleselect;

import com.chemcool.school.tasks.domain.chemsingleselect.ChemSingleSelectTaskEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemSingleSelectTaskEventJournal extends JpaRepository<ChemSingleSelectTaskEvent, String> {
}
