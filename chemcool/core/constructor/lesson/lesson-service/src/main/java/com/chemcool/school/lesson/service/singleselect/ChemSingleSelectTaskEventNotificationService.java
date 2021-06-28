package com.chemcool.school.lesson.service.singleselect;


import com.chemcool.school.lesson.domain.singleselect.ChemSingleSelectTaskEvent;

public interface ChemSingleSelectTaskEventNotificationService {
    void send(ChemSingleSelectTaskEvent event);
}
