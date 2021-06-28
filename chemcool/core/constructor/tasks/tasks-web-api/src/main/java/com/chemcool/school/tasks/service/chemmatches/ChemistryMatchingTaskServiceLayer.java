package com.chemcool.school.tasks.service.chemmatches;

import com.chemcool.school.tasks.chemMatches.ChemistryTaskEmptyException;
import com.chemcool.school.tasks.domain.chemmatches.ChemistryMatchingTask;
import com.chemcool.school.tasks.domain.chemmatches.ChemistryMatchingTaskExample;
import com.chemcool.school.tasks.dto.chemmatches.ChemistryMatchingTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChemistryMatchingTaskServiceLayer {

    private final ChemistryMatchingTaskProxyService service;

    public ChemistryMatchingTaskDto getTaskDtoById(String id) {
        return service.getById(id).map(ChemistryMatchingTaskDto::new).orElse(null);
    }

    public List<ChemistryMatchingTaskDto> getAllTasks() {
        List<ChemistryMatchingTaskDto> list = new ArrayList<>();
        for (ChemistryMatchingTask task : service.getAll()) {
            list.add(new ChemistryMatchingTaskDto(task));
        }
        return list;
    }

    public List<ChemistryMatchingTaskDto> getAllTasksByChapterId(Integer chapterId) {
        List<ChemistryMatchingTaskDto> list = new ArrayList<>();
        for (ChemistryMatchingTask task : service.getAllByChapterId(chapterId)) {
            list.add(new ChemistryMatchingTaskDto(task));
        }
        return list;
    }

    public String add(ChemistryMatchingTaskDto dto) {
        validate(dto);
        return service.add(
                new ChemistryMatchingTaskExample(
                        dto.getTaskId(),
                        dto.getDescription(),
                        dto.getChapterId(),
                        dto.getReferenceId(),
                        dto.getCoupleForMatchingList()
                )
        );
    }

    public void update(ChemistryMatchingTaskDto dto) {
        validate(dto);
        service.update(
                new ChemistryMatchingTask(
                        dto.getTaskId(),
                        dto.getDescription(),
                        dto.getChapterId(),
                        dto.getReferenceId(),
                        dto.getCoupleForMatchingList()
                )
        );
    }

    public void deleteById(String id) {
        service.deleteById(id);
    }

    private void validate(ChemistryMatchingTaskDto dto) {
        if (dto.getDescription().isEmpty()) {
            throw new ChemistryTaskEmptyException("Необходимые поля пустые, проверьте пожалуйста бланк заполнения задания.");
        }
    }
}
