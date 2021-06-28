package com.chemcool.school.lesson.service.matches;


import com.chemcool.school.lesson.domain.matches.ChemMatchingTask;
import com.chemcool.school.lesson.storage.matches.ChemMatchingTaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChemMatchingTaskService {

    private final ChemMatchingTaskRepository repository;

    public void save(ChemMatchingTask task) {
        repository.save(task);
        log.info("Добавлена задача с UUID = " + task.getTaskId() );
    }

    public Optional<ChemMatchingTask> getById(String id) {
        return repository.findById(id);
    }

    public List<ChemMatchingTask> getAll() {
        return repository.findAll();
    }


    public void update(ChemMatchingTask task) {
        log.info("Обновлена задача с UUID = " + task.getTaskId() );
        repository.save(task);
    }

    public void deleteById(String id) {
        log.info("Удалена задачу с UUID = " + id);
        repository.deleteById(id);
    }

    public List<ChemMatchingTask> getAllByChapterId(int chapterId) {
        log.info("Найдены задачи Matching с chapterId = " + chapterId );
        return repository.getAllByChapterId(chapterId);
    }

    public List<ChemMatchingTask> getAllByReferenceId(int referenceId){
        log.info("Найдены задачи Matching с referenceId = " + referenceId );
        return repository.getAllByReferenceId(referenceId);
    }

    public List<ChemMatchingTask> getAllByReferenceIdAndChapterId(int referenceId, int chapterId){
        log.info("Найдены задачи Matching с referenceId = " + referenceId + " и chapterId =  " + chapterId );
        return repository.getAllByReferenceIdAndChapterId(referenceId, chapterId);
    }
}
/*


 */