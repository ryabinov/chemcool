package com.chemcool.school.tasks.service.chemfixedanswer;


import com.chemcool.school.tasks.domain.chemfixedanswer.ChemFixedAnswerTaskEvent;

public interface ChemFixedAnswerTaskEventNotificationService {
    void send(ChemFixedAnswerTaskEvent event);
}
