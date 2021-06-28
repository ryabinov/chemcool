package com.chemcool.school.lesson.web.api.controllers;

import com.chemcool.school.lesson.web.api.dto.ChemSingleSelectTaskDto;
import com.chemcool.school.lesson.web.api.service.ChemSingleSelectTaskPresentation;
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
public class ChemSingleSelectRestController {
    private final ChemSingleSelectTaskPresentation presentation;

    @ApiOperation("Возвращает сущности задания типа \"Один ответ\" по главе")
    @GetMapping("/findSingleSelectTaskByChapterId")
    public List<ChemSingleSelectTaskDto> findTaskByChapterId(int chapterId){
        return presentation.getAllTasksByChapterIdDto(chapterId);
    }

    @ApiOperation("Возвращает сущности задания типа \"Один ответ\" по разделу")
    @GetMapping("/findSingleSelectTaskByReferenceId")
    public List<ChemSingleSelectTaskDto> findTaskByReferenceId(int referenceId){
        return presentation.getAllTasksByReferenceIdDto(referenceId);
    }

    @ApiOperation("Возвращает сущности задания типа \"Один ответ\" по разделу и главе")
    @GetMapping("/findSingleSelectTaskByReferenceIdAndChapterId")
    public List<ChemSingleSelectTaskDto> findTaskByReferenceIdAndChapterId(int referenceId, int chapterId){
        return presentation.getAllTasksByReferenceIdAndChapterIdDto(referenceId, chapterId);
    }
}
