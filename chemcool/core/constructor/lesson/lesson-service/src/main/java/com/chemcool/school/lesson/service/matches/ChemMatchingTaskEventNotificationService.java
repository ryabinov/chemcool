package com.chemcool.school.lesson.service.matches;

import com.chemcool.school.lesson.domain.matches.ChemMatchingTaskEvent;

public interface ChemMatchingTaskEventNotificationService {
    void send(ChemMatchingTaskEvent event);
}
