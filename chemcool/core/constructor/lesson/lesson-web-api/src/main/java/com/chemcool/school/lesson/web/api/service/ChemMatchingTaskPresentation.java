package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.web.api.dto.ChemMatchingTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChemMatchingTaskPresentation {

    private final ChemMatchingTaskServiceLayer service;

    public ChemMatchingTaskDto getTaskDtoByIdDto(String id) {
        return service.getTaskDtoByIdDto(id);
    }

    public List<ChemMatchingTaskDto> getAllTasksDto() {
        return service.getAllTasksDto();
    }

    public List<ChemMatchingTaskDto> getAllTasksByReferenceIdDto(int referenceId) {
        return service.getAllTasksByReferenceIdDto(referenceId);
    }

    public List<ChemMatchingTaskDto> getAllTasksByChapterIdDto(int chapterId) {
        return service.getAllTasksByChapterIdDto(chapterId);
    }

    public List<ChemMatchingTaskDto> getAllTasksByReferenceIdAndChapterIdDto(int referenceId, int chapterId) {
        return service.getAllTasksByReferenceIdAndChapterIdDto(referenceId, chapterId);
    }

}
