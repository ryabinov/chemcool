package com.chemcool.school.lesson.service.fixedanswer;

import com.chemcool.school.lesson.domain.fixedanswer.ChemFixedAnswerTaskEvent;

public interface ChemFixedAnswerTaskEventNotificationService {
    void send(ChemFixedAnswerTaskEvent event);
}
