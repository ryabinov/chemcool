package com.chemcool.school.tasks.service.chemmatches;

import com.chemcool.school.tasks.domain.chemmatches.ChemistryMatchingTaskEvent;
import com.chemcool.school.tasks.infrastructure.storage.chemmatches.ChemistryMatchingTaskEventJournal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChemistryMatchingTaskEventService {

    private final ChemistryMatchingTaskEventJournal journal;

     public void handleEvent(ChemistryMatchingTaskEvent event) {
        journal.save(event);
    }
}
