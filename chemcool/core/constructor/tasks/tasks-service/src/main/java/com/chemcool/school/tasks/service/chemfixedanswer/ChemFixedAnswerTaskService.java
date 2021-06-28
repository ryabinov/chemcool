package com.chemcool.school.tasks.service.chemfixedanswer;


import com.chemcool.school.tasks.domain.chemfixedanswer.ChemFixedAnswerTask;
import com.chemcool.school.tasks.infrastructure.storage.chemfixedanswer.ChemFixedAnswerTaskRepository;
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
        log.info("Задача с ID: " + task.getTaskId() + " сохранена.");
        return task.getTaskId();
    }

    public Optional<ChemFixedAnswerTask> getById(String id) {
        return repository.findById(id);
    }

    public List<ChemFixedAnswerTask> getAll() {
        return repository.findAll();
    }

    public List<ChemFixedAnswerTask> getAllByChapterId(int chapterId) {
        return repository.findAllByChapterId(chapterId);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
