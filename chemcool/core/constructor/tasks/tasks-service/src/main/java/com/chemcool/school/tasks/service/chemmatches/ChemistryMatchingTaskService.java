package com.chemcool.school.tasks.service.chemmatches;


import com.chemcool.school.tasks.domain.chemmatches.ChemistryMatchingTask;
import com.chemcool.school.tasks.infrastructure.storage.chemmatches.ChemistryMatchingTaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChemistryMatchingTaskService {

    private final ChemistryMatchingTaskRepository repository;

    public void handleTask(ChemistryMatchingTask task) {
        repository.save(task);
        log.info("Добавлена задача с UUID = " + task.getTaskId() );
    }

    public Optional<ChemistryMatchingTask> getById(String id) {
        return repository.findById(id);
    }

    public List<ChemistryMatchingTask> getAll() {
        return repository.findAll();
    }

    public List<ChemistryMatchingTask> getAllByChapterId(int chapterId) {
        return repository.getAllByChapterId(chapterId);
    }

    public void update(ChemistryMatchingTask task) {
        log.info("Обновлена задача с UUID = " + task.getTaskId() );
        repository.save(task);
    }

    public void deleteById(String id) {
        log.info("Удалена задачу с UUID = " + id);
        repository.deleteById(id);
    }
}
