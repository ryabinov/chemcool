package com.chemcool.school.lesson.service.equation;

import com.chemcool.school.lesson.domain.equation.ChemEquationsTask;
import com.chemcool.school.lesson.storage.equation.ChemEquationsTaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChemEquationsTaskService {

    private final ChemEquationsTaskRepository repository;
    private final ChemEquationsTaskAnswerCheckService checkService;

    public String save(ChemEquationsTask task) {
        repository.save(task);
        log.info("Задача с ID: " + task.getTaskId() + " сохранена.");
        return task.getTaskId();
    }

    public Optional<ChemEquationsTask> getById(String id) {
        return repository.findById(id);
    }

    public List<ChemEquationsTask> getAll() {
        return repository.findAll();
    }

    public List<ChemEquationsTask> getAllByChapterId(int chapterId) {
        return repository.findAllByChapterId(chapterId);
    }

    public List<ChemEquationsTask> getAllByReferenceIdAndChapterId(int referenceId, int chapterId) {
        return repository.findAllByReferenceIdAndChapterId(referenceId,chapterId);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }

    public boolean[] checkAnswer(String taskId, String userAnswer) {
        return checkService.checkAnswer(repository.getOne(taskId).getRightAnswer(), userAnswer);
    }

    public List<ChemEquationsTask> getAllByReferenceId(int referenceId) {
        return repository.findAllByReferenceId(referenceId);
    }
}

