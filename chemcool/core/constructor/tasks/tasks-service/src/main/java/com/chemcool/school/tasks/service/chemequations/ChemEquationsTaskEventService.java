package com.chemcool.school.tasks.service.chemequations;

import com.chemcool.school.tasks.domain.chemequations.ChemEquationsTaskEvent;
import com.chemcool.school.tasks.infrastructure.storage.chemequations.ChemEquationsTaskEventJournal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChemEquationsTaskEventService {

    private final ChemEquationsTaskEventJournal eventJournal;

    public void handleEvent(ChemEquationsTaskEvent event) {
        eventJournal.save(event);
        log.info("Событие отправлено на сохранение");
    }
}