package com.chemcool.school.answers.service.matches;

import com.chemcool.school.answers.storage.matches.ChemistryMatchingTaskEventJournal;
import com.chemcool.school.answers.domain.matches.ChemistryMatchingTaskEvent;
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
