package com.chemcool.school.theory.service;

import com.chemcool.school.theory.domain.ChemistryTheoryEvent;
import com.chemcool.school.theory.storage.ChemistryTheoryEventJournal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ChemistryTheoryEventService {
    private final ChemistryTheoryEventJournal journal;

    public void handleEvent(ChemistryTheoryEvent event){
        journal.save(event);
    }
}
