package com.chemcool.school.tasks.service.chemsingleselect;



import com.chemcool.school.tasks.domain.chemsingleselect.ChemSingleSelectTask;
import com.chemcool.school.tasks.infrastructure.storage.chemsingleselect.ChemSingleSelectTaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChemSingleSelectTaskService {

    private final ChemSingleSelectTaskRepository repository;

    public void save(ChemSingleSelectTask task) {
        repository.save(task);
        log.info("Добавлена задача с UUID = " + task.getTaskId() );
    }

    public Optional<ChemSingleSelectTask> getById(String id) {
        return repository.findById(id);
    }

    public List<ChemSingleSelectTask> getAll() {
        return repository.findAll();
    }

    public List<ChemSingleSelectTask> getAllByChapterId(int chapterId) {
        return repository.getAllByChapterId(chapterId);
    }

    public void update(ChemSingleSelectTask task) {
        log.info("Обновлена задача с UUID = " + task.getTaskId() );
        repository.save(task);
    }

    public void deleteById(String id) {
        log.info("Удалена задачу с UUID = " + id);
        repository.deleteById(id);
    }
}
