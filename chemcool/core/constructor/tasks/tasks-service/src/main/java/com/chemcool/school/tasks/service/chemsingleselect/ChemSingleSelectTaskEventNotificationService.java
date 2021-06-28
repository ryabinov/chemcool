package com.chemcool.school.tasks.service.chemsingleselect;


import com.chemcool.school.tasks.domain.chemsingleselect.ChemSingleSelectTaskEvent;

public interface ChemSingleSelectTaskEventNotificationService {
    void send(ChemSingleSelectTaskEvent event);
}
