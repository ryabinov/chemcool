package com.chemcool.school.tasks.service.chemmatches;

import com.chemcool.school.tasks.dto.chemmatches.ChemistryMatchingTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChemistryMatchingTaskPresentation {

    private final ChemistryMatchingTaskServiceLayer service;

    public ChemistryMatchingTaskDto getTaskDtoById(String id) {
        return service.getTaskDtoById(id);
    }

    public List<ChemistryMatchingTaskDto> getAllTasks() {
        return service.getAllTasks();
    }

    public List<ChemistryMatchingTaskDto> getAllTasksByChapterId(Integer chapterId) {
        return service.getAllTasksByChapterId(chapterId);
    }

    public String add(ChemistryMatchingTaskDto dto) {
       return service.add(dto);
    }

    public void update(ChemistryMatchingTaskDto dto) {
       service.update(dto);
    }

    public void deleteById(String id) {
        service.deleteById(id);
    }

}
