package com.chemcool.school.lesson.service.matches;

import com.chemcool.school.lesson.domain.matches.ChemMatchingTaskEvent;
import com.chemcool.school.lesson.storage.matches.ChemMatchingTaskEventJournal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChemMatchingTaskEventService {

    private final ChemMatchingTaskEventJournal journal;

     public void handleEvent(ChemMatchingTaskEvent event) {
        journal.save(event);
    }
}
