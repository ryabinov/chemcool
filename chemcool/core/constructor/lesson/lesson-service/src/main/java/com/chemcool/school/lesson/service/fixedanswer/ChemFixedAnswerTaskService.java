package com.chemcool.school.lesson.service.fixedanswer;

import com.chemcool.school.lesson.domain.fixedanswer.ChemFixedAnswerTask;
import com.chemcool.school.lesson.storage.fixedanswer.ChemFixedAnswerTaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChemFixedAnswerTaskService {

    private final ChemFixedAnswerTaskRepository repository;

    public String save(ChemFixedAnswerTask task) {
        repository.save(task);
        log.info("Добавлена задача с UUID = " + task.getTaskId() );
        return task.getTaskId();
    }

    public Optional<ChemFixedAnswerTask> getById(String id) {
        return repository.findById(id);
    }

    public List<ChemFixedAnswerTask> getAll() {
        return repository.findAll();
    }

    public List<ChemFixedAnswerTask> getAllByChapterId(int chapterId) {
        return repository.getAllByChapterId(chapterId);
    }

    public void update(ChemFixedAnswerTask task) {
        log.info("Обновлена задача с UUID = " + task.getTaskId() );
        repository.save(task);
    }

    public void deleteById(String id) {
        log.info("Удалена задачу с UUID = " + id);
        repository.deleteById(id);
    }

    public List<ChemFixedAnswerTask> getAllByReferenceId(int referenceId){
        return repository.getAllByReferenceId(referenceId);
    }

    public List<ChemFixedAnswerTask> getAllByReferenceIdAndChapterId(int referenceId, int chapterId){
        return repository.getAllByReferenceIdAndChapterId(referenceId, chapterId);
    }
}
