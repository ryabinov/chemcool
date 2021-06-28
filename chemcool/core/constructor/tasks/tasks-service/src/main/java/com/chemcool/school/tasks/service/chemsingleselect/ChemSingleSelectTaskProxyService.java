package com.chemcool.school.tasks.service.chemsingleselect;

import com.chemcool.school.tasks.domain.chemsingleselect.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChemSingleSelectTaskProxyService {
    @Qualifier("KafkaChemSingleSelectTaskEventNotificationService")
    private final ChemSingleSelectTaskEventNotificationService notificationService;
    private final ChemSingleSelectTaskService taskService;

    public String add(ChemSingleSelectTaskExample exampleTask) {
        ChemSingleSelectTask task = ChemSingleSelectTaskFactory.createTask(exampleTask);
        notificationService.send(
                ChemSingleSelectTaskEventFactory.createEvent(task, ChemTaskEventType.CREATE)
        );
        return task.getTaskId();
    }

    public Optional<ChemSingleSelectTask> getById(String id) {
        return taskService.getById(id);
    }

    public List<ChemSingleSelectTask> getAll() {
        return taskService.getAll();
    }

    public List<ChemSingleSelectTask> getAllByChapterId(int chapterId) {
        return taskService.getAllByChapterId(chapterId);
    }

    public void update(ChemSingleSelectTask task) {
        notificationService.send(
                ChemSingleSelectTaskEventFactory.createEvent(task, ChemTaskEventType.UPDATE)
        );
    }

    public void deleteById(String id) {
        taskService.deleteById(id);
    }
}
