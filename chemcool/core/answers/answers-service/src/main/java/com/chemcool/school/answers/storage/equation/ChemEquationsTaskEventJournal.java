package com.chemcool.school.answers.storage.equation;

import com.chemcool.school.answers.domain.equation.ChemEquationsTaskEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChemEquationsTaskEventJournal
        extends JpaRepository<ChemEquationsTaskEvent, String> {
}
