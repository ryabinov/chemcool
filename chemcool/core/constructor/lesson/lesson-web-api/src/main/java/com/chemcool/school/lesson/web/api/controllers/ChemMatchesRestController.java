package com.chemcool.school.lesson.web.api.controllers;



import com.chemcool.school.lesson.web.api.dto.ChemMatchingTaskDto;
import com.chemcool.school.lesson.web.api.service.ChemMatchingTaskPresentation;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/v1.0"})
@RequiredArgsConstructor
public class ChemMatchesRestController {
    private final ChemMatchingTaskPresentation presentation;

    @ApiOperation("Возвращает сущности задания типа \"Сопоставление\" по разделу")
    @GetMapping("/findMatchesTaskByReferenceId")
    public List<ChemMatchingTaskDto> findTaskByReferences(int referenceId){
        return presentation.getAllTasksByReferenceIdDto(referenceId);
    }

    @ApiOperation("Возвращает сущности задания типа \"Сопоставление\" по главе")
    @GetMapping("/findMatchesTaskByChapterId")
    public List<ChemMatchingTaskDto> findTaskByChapter(int chapterId){
        return presentation.getAllTasksByChapterIdDto(chapterId);
    }

    @ApiOperation("Возвращает сущности задания типа \"Сопоставление\" по разделу и главе")
    @GetMapping("/findMatchesTaskByReferenceIdAndChapterId")
    public List<ChemMatchingTaskDto> findTaskByReferenceIdAndChapterId(int referenceId, int chapterId){
        return presentation.getAllTasksByReferenceIdAndChapterIdDto(referenceId, chapterId);
    }
}
