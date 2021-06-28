package com.chemcool.school.theory.service;

import com.chemcool.school.theory.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChemistryTheoryProxyService {
    private final ChemistryTheoryEventNotificationService notificationService;
    private final ChemistryTheoryPageService pageService;

    public String add(ChemistryTheoryExample example) {
        ChemistryTheory theory = ChemistryTheoryFactory.createTheory(example);
        ChemistryTheoryEvent chemistryTheoryEvent = ChemistryTheoryEventFactory.createEvent(theory, ChemistryTheoryEventType.CREATED);

        notificationService.send(chemistryTheoryEvent);
        return theory.getTheoryId();
    }

    public void delete(ChemistryTheory theory){
        pageService.delete(theory);
    }

    public void update(ChemistryTheory theory){
        notificationService.send(
                ChemistryTheoryEventFactory.createEvent(theory, ChemistryTheoryEventType.UPDATED)
        );
    }

    public ChemistryTheory findTheoryById(String theoryId) {
        //  TODO проверить на то, что lessonId не пустой.
        if (theoryId == null || theoryId.isEmpty()) {
            throw new RuntimeException("theoryId параметр пустой, проверьте конфигурацию.");
        }
        return pageService.findTheoryById(theoryId);
    }
}

