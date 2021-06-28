package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.domain.matches.ChemMatchingTask;
import com.chemcool.school.lesson.service.matches.ChemMatchingTaskProxyService;
import com.chemcool.school.lesson.web.api.dto.ChemMatchingTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChemMatchingTaskServiceLayer {

    private final ChemMatchingTaskProxyService service;

    public ChemMatchingTaskDto getTaskDtoByIdDto(String id) {
        return service.getById(id).map(ChemMatchingTaskDto::new).orElse(null);
    }

    public List<ChemMatchingTaskDto> getAllTasksDto() {
        List<ChemMatchingTaskDto> list = new ArrayList<>();
        for (ChemMatchingTask task : service.getAll()) {
            list.add(new ChemMatchingTaskDto(task));
        }
        return list;
    }
    public List<ChemMatchingTaskDto> getAllTasksByReferenceIdDto(int referenceId) {
        List<ChemMatchingTaskDto> list = new ArrayList<>();
        for (ChemMatchingTask task : service.getAllByReferenceId(referenceId)) {
            list.add(new ChemMatchingTaskDto(task));
        }
        return list;
    }

    public List<ChemMatchingTaskDto> getAllTasksByChapterIdDto(int chapterId) {
        List<ChemMatchingTaskDto> list = new ArrayList<>();
        for (ChemMatchingTask task : service.getAllByChapterId(chapterId)) {
            list.add(new ChemMatchingTaskDto(task));
        }
        return list;
    }

    public List<ChemMatchingTaskDto> getAllTasksByReferenceIdAndChapterIdDto(int referenceId, int chapterId) {
        List<ChemMatchingTaskDto> list = new ArrayList<>();
        for (ChemMatchingTask task : service.getAllByReferenceIdAndChapterId(referenceId, chapterId)) {
            list.add(new ChemMatchingTaskDto(task));
        }
        return list;
    }
}
