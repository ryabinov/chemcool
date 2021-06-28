package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.web.api.dto.ChemSingleSelectTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChemSingleSelectTaskPresentation {

    private final ChemSingleSelectTaskServiceLayer serviceLayer;

    public ChemSingleSelectTaskDto getTaskDtoByIdDto(String id) {
        return serviceLayer.getTaskDtoByIdDto(id);
    }

    public List<ChemSingleSelectTaskDto> getAllTasksDto() {
        return serviceLayer.getAllTasksDto();
    }

    public List<ChemSingleSelectTaskDto> getAllTasksByReferenceIdDto(int referenceId) {
        return serviceLayer.getAllTasksByReferenceIdDto(referenceId);
    }

    public List<ChemSingleSelectTaskDto> getAllTasksByChapterIdDto(int chapterId) {
        return serviceLayer.getAllTasksByChapterIdDto(chapterId);
    }

    public List<ChemSingleSelectTaskDto> getAllTasksByReferenceIdAndChapterIdDto(int referenceId, int chapterId) {
        return serviceLayer.getAllTasksByReferenceIdAndChapterIdDto(referenceId, chapterId);
    }
}
