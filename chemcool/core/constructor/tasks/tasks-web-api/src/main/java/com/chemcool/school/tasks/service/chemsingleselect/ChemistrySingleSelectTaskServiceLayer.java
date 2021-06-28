package com.chemcool.school.tasks.service.chemsingleselect;


import com.chemcool.school.tasks.chemsingleselect.ChemistryTaskEmptyException;
import com.chemcool.school.tasks.domain.chemsingleselect.ChemSingleSelectTask;
import com.chemcool.school.tasks.domain.chemsingleselect.ChemSingleSelectTaskExample;
import com.chemcool.school.tasks.dto.chemsingleselect.ChemistrySingleSelectTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChemistrySingleSelectTaskServiceLayer {

    private final ChemSingleSelectTaskProxyService service;

    public ChemistrySingleSelectTaskDto getTaskDtoById(String id) {
        return service.getById(id).map(ChemistrySingleSelectTaskDto::new).orElse(null);
    }

    public List<ChemistrySingleSelectTaskDto> getAllTasks() {
        List<ChemistrySingleSelectTaskDto> list = new ArrayList<>();
        for (ChemSingleSelectTask task : service.getAll()) {
            list.add(new ChemistrySingleSelectTaskDto(task));
        }
        return list;
    }

    public List<ChemistrySingleSelectTaskDto> getAllTasksByChapterId(int chapterId) {
        List<ChemistrySingleSelectTaskDto> list = new ArrayList<>();
        for (ChemSingleSelectTask task : service.getAllByChapterId(chapterId)) {
            list.add(new ChemistrySingleSelectTaskDto(task));
        }
        return list;
    }

    public String add(ChemistrySingleSelectTaskDto dto) {
        validate(dto);
        return service.add(
                new ChemSingleSelectTaskExample(
                        dto.getDescription(),
                        dto.getCorrectAnswer(),
                        dto.getChapterId(),
                        dto.getReferenceId(),
                        dto.getIncorrectAnswerOne(),
                        dto.getIncorrectAnswerTwo(),
                        dto.getIncorrectAnswerThree(),
                        dto.getIncorrectAnswerFour()
                )
        );
    }

    public void update(ChemistrySingleSelectTaskDto dto) {
        validate(dto);
        service.update(
                new ChemSingleSelectTask(
                        dto.getTaskDtoId(),
                        dto.getDescription(),
                        dto.getCorrectAnswer(),
                        dto.getChapterId(),
                        dto.getReferenceId(),
                        dto.getIncorrectAnswerOne(),
                        dto.getIncorrectAnswerTwo(),
                        dto.getIncorrectAnswerThree(),
                        dto.getIncorrectAnswerFour()
                )
        );
    }

    public void deleteById(String id) {
        service.deleteById(id);
    }

    private void validate(ChemistrySingleSelectTaskDto dto) {
        if (
                dto.getDescription().isEmpty() //todo добавить все обязательные поля для проверки заполнения
        ) {
            throw new ChemistryTaskEmptyException("Необходимые поля пустые, проверьте пожалуйста бланк заполнения задания.");
        }
    }
}
