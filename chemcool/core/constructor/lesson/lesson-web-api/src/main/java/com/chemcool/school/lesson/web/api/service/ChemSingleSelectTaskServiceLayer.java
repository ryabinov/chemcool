package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.domain.singleselect.ChemSingleSelectTask;
import com.chemcool.school.lesson.service.singleselect.ChemSingleSelectTaskProxyService;
import com.chemcool.school.lesson.web.api.dto.ChemSingleSelectTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChemSingleSelectTaskServiceLayer {

    private final ChemSingleSelectTaskProxyService service;

    public ChemSingleSelectTaskDto getTaskDtoByIdDto(String id) {
        return service.getById(id).map(ChemSingleSelectTaskDto::new).orElse(null);
    }

    public List<ChemSingleSelectTaskDto> getAllTasksDto() {
        List<ChemSingleSelectTaskDto> list = new ArrayList<>();
        for (ChemSingleSelectTask task : service.getAll()) {
            list.add(new ChemSingleSelectTaskDto(task));
        }
        return list;
    }

    public List<ChemSingleSelectTaskDto> getAllTasksByReferenceIdDto(int referenceId) {
        List<ChemSingleSelectTaskDto> list = new ArrayList<>();
        for (ChemSingleSelectTask task : service.getAllByReferenceId(referenceId)) {
            list.add(new ChemSingleSelectTaskDto(task));
        }
        return list;
    }

    public List<ChemSingleSelectTaskDto> getAllTasksByChapterIdDto(int chapterId) {
        List<ChemSingleSelectTaskDto> list = new ArrayList<>();
        for (ChemSingleSelectTask task : service.getAllByChapterId(chapterId)) {
            list.add(new ChemSingleSelectTaskDto(task));
        }
        return list;
    }

    public List<ChemSingleSelectTaskDto> getAllTasksByReferenceIdAndChapterIdDto(int referenceId, int chapterId) {
        List<ChemSingleSelectTaskDto> list = new ArrayList<>();
        for (ChemSingleSelectTask task : service.getAllByReferenceIdAndChapterId(referenceId, chapterId)) {
            list.add(new ChemSingleSelectTaskDto(task));
        }
        return list;
    }
}
