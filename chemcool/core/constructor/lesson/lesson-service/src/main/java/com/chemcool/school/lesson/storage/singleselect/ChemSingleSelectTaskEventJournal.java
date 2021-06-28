package com.chemcool.school.lesson.storage.singleselect;


import com.chemcool.school.lesson.domain.singleselect.ChemSingleSelectTaskEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChemSingleSelectTaskEventJournal extends JpaRepository<ChemSingleSelectTaskEvent, String> {
}
