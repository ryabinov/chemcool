package com.chemcool.school.theory.storage;

import com.chemcool.school.theory.domain.ChemistryTheoryEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemistryTheoryEventJournal extends JpaRepository<ChemistryTheoryEvent, String> {
}
