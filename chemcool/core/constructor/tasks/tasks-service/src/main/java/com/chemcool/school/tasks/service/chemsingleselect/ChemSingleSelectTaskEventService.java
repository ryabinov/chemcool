package com.chemcool.school.tasks.service.chemsingleselect;


import com.chemcool.school.tasks.domain.chemsingleselect.ChemSingleSelectTaskEvent;
import com.chemcool.school.tasks.infrastructure.storage.chemsingleselect.ChemSingleSelectTaskEventJournal;
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
