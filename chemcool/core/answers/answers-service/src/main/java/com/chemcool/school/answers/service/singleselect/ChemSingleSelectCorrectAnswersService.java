package com.chemcool.school.answers.service.singleselect;

import com.chemcool.school.answers.storage.singleselect.ChemSingleSelectCorrectAnswerRepository;
import com.chemcool.school.answers.domain.singleselect.ChemSingleSelectTask;
import com.chemcool.school.answers.domain.singleselect.ChemSingleSelectCorrectAnswers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChemSingleSelectCorrectAnswersService {
    private final ChemSingleSelectCorrectAnswerRepository repository;

    public void saveCorrectAnswers(ChemSingleSelectTask task) {
        repository.save(ChemSingleSelectCorrectAnswers.createChemSingleSelectCorrectAnswers(task));
    }

    public String getCorrectAnswerByIdTask(String taskId) {
        ChemSingleSelectCorrectAnswers сorrectAnswers = repository.findById(taskId).orElseThrow(()-> new IllegalArgumentException("Нет задачи с таким id"));
        return сorrectAnswers.getCorrectAnswer();
    }
}
