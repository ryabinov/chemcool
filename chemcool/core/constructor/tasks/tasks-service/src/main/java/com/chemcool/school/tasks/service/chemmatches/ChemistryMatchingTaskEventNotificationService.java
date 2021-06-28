package com.chemcool.school.tasks.service.chemmatches;


import com.chemcool.school.tasks.domain.chemmatches.ChemistryMatchingTaskEvent;

public interface ChemistryMatchingTaskEventNotificationService {
    void send(ChemistryMatchingTaskEvent event);
}
