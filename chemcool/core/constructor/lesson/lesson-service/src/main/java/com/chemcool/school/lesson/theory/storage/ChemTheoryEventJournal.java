package com.chemcool.school.lesson.theory.storage;


import com.chemcool.school.lesson.theory.domain.ChemTheoryEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemTheoryEventJournal extends JpaRepository<ChemTheoryEvent, String> {
}
