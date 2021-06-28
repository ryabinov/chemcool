package com.chemcool.school.lesson.theory.service;


import com.chemcool.school.lesson.theory.domain.ChemTheoryEvent;
import com.chemcool.school.lesson.theory.storage.ChemTheoryEventJournal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class ChemTheoryEventService {

    private final ChemTheoryEventJournal journal;

    public void handleEvent(ChemTheoryEvent event){
        journal.save(event);
    }
}
