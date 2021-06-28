package com.chemcool.school.tasks.service.chemsingleselect;

import com.chemcool.school.tasks.dto.chemsingleselect.ChemistrySingleSelectTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChemistrySingleSelectTaskPresentation {

    private final ChemistrySingleSelectTaskServiceLayer serviceLayer;

    public ChemistrySingleSelectTaskDto getTaskDtoById(String id) {
        return serviceLayer.getTaskDtoById(id);
    }

    public List<ChemistrySingleSelectTaskDto> getAllTasks() {
        return serviceLayer.getAllTasks();
    }

    public List<ChemistrySingleSelectTaskDto> getAllTasksByChapterId(int chapterId) {
        return serviceLayer.getAllTasksByChapterId(chapterId);
    }

    public String add(ChemistrySingleSelectTaskDto dto) {
        return serviceLayer.add(dto);
    }

    public void update(ChemistrySingleSelectTaskDto dto) {
        serviceLayer.update(dto);
    }

    public void deleteById(String id) {
        serviceLayer.deleteById(id);
    }
}
