package com.chemcool.school.answers.service.fixedanswer;

import com.chemcool.school.answers.domain.fixedanswer.ChemFixedAnswerTaskEvent;
import com.chemcool.school.answers.storage.fixedanswer.ChemFixedAnswerTaskEventJournal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChemFixedAnswerTaskEventService {

    private final ChemFixedAnswerTaskEventJournal eventJournal;

    public void handleEvent(ChemFixedAnswerTaskEvent event) {
        eventJournal.save(event);
        log.info("Событие отправлено на сохранение");
    }
}
