package com.chemcool.school.tasks.service.chemfixedanswer;


import com.chemcool.school.tasks.domain.chemfixedanswer.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ChemFixedAnswerTaskProxyService {


    private final ChemFixedAnswerTaskEventNotificationService notificationService;
    private final ChemFixedAnswerTaskService taskService;

    public String add(ChemFixedAnswerTaskExample exampleTask) {
        ChemFixedAnswerTask task = ChemFixedAnswerTaskFactory.createChemistryFixedAnswerTask(exampleTask);
        notificationService.send(ChemFixedAnswerTaskEventFactory.createTaskEvent(task, ChemFixedAnswerTaskEventType.CREATE));
        return task.getTaskId();
    }

    public void update(ChemFixedAnswerTask task) {
        notificationService.send(ChemFixedAnswerTaskEventFactory.createTaskEvent(task, ChemFixedAnswerTaskEventType.UPDATE));
    }

    public Optional<ChemFixedAnswerTask> getById(String id) {
        return taskService.getById(id);
    }

    public List<ChemFixedAnswerTask> getAll() {
        return taskService.getAll();
    }

    public List<ChemFixedAnswerTask> getAllByChapterId(int chapterId) {
        return taskService.getAllByChapterId(chapterId);
    }

    public void deleteById(String id) {
        taskService.deleteById(id);
    }
}

