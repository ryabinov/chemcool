package com.chemcool.school.lesson.service.singleselect;

import com.chemcool.school.lesson.domain.singleselect.ChemSingleSelectTaskEvent;
import com.chemcool.school.lesson.storage.singleselect.ChemSingleSelectTaskEventJournal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChemSingleSelectTaskEventService {

    private final ChemSingleSelectTaskEventJournal journal;

     public void handleEvent(ChemSingleSelectTaskEvent event) {
        journal.save(event);
    }
}
