package com.chemcool.school.lesson.service.matches;

import com.chemcool.school.lesson.domain.matches.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChemMatchingTaskProxyService {

    private final ChemMatchingTaskEventNotificationService notificationService;
    private final ChemMatchingTaskService taskService;

    public String add(ChemMatchingTaskExample exampleTask) {
        ChemMatchingTask task = ChemMatchingTaskFactory.createTask(exampleTask);
        notificationService.send(
                ChemMatchingTaskEventFactory.createEvent(task, ChemMatchingTaskEventType.CREATED)
        );
        return task.getTaskId();
    }

    public Optional<ChemMatchingTask> getById(String id) {
        return taskService.getById(id);
    }

    public List<ChemMatchingTask> getAll() {
        return taskService.getAll();
    }

    public List<ChemMatchingTask> getAllByChapterId(int chapterId) {
        return taskService.getAllByChapterId(chapterId);
    }

    public List<ChemMatchingTask> getAllByReferenceId(int referenceId) {
        return taskService.getAllByReferenceId(referenceId);
    }

    public List<ChemMatchingTask> getAllByReferenceIdAndChapterId(int referenceId, int chapterId) {
        return taskService.getAllByReferenceIdAndChapterId(referenceId, chapterId);
    }

    public void update(ChemMatchingTask task) {
        notificationService.send(
                ChemMatchingTaskEventFactory.createEvent(task, ChemMatchingTaskEventType.UPDATED)
        );
    }

    public void deleteById(String id) {
        taskService.deleteById(id);
    }
}
