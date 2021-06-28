package com.chemcool.school.tasks.service.chemmatches;


import com.chemcool.school.tasks.domain.chemmatches.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChemistryMatchingTaskProxyService {

    private final ChemistryMatchingTaskEventNotificationService notificationService;
    private final ChemistryMatchingTaskService taskService;

    public String add(ChemistryMatchingTaskExample exampleTask) {
        ChemistryMatchingTask task = ChemistryMatchingTaskFactory.createTask(exampleTask);
        notificationService.send(
                ChemistryMatchingTaskEventFactory.createEvent(task, ChemistryMatchingTaskEventType.CREATED)
        );
        return task.getTaskId();
    }

    public Optional<ChemistryMatchingTask> getById(String id) {
        return taskService.getById(id);
    }

    public List<ChemistryMatchingTask> getAll() {
        return taskService.getAll();
    }

    public List<ChemistryMatchingTask> getAllByChapterId(int chapterId) {
        return taskService.getAllByChapterId(chapterId);
    }

    public void update(ChemistryMatchingTask task) {
        notificationService.send(
                ChemistryMatchingTaskEventFactory.createEvent(task, ChemistryMatchingTaskEventType.UPDATED)
        );
    }

    public void deleteById(String id) {
        taskService.deleteById(id);
    }
}
