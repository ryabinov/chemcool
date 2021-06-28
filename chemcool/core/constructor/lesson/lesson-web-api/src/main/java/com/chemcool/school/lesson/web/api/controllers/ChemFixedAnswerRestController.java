package com.chemcool.school.lesson.web.api.controllers;

import com.chemcool.school.lesson.web.api.dto.ChemFixedAnswerTaskDto;
import com.chemcool.school.lesson.web.api.service.ChemFixedAnswerTaskPresentation;
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
public class ChemFixedAnswerRestController {
    private final ChemFixedAnswerTaskPresentation presentation;

    @ApiOperation("Возвращает сущности задания типа \"Фиксированный ответ\" по разделу")
    @GetMapping("/findFixedAnswerTaskByReferenceId")
    public List<ChemFixedAnswerTaskDto> findTaskByReferenceId(int referenceId){
        return presentation.getAllChemistryFixedAnswerByReferenceIdDto(referenceId);
    }

    @ApiOperation("Возвращает сущности задания типа \"Фиксированный ответ\" по главе")
    @GetMapping("/findFixedAnswerTaskByChapterId")
    public List<ChemFixedAnswerTaskDto> findTaskByChapterId(int chapterId){
        return presentation.getAllChemistryFixedAnswerByChapterIdDto(chapterId);
    }

    @ApiOperation("Возвращает сущности задания типа \"Фиксированный ответ\" по разделу и главе")
    @GetMapping("/findFixedAnswerTaskByReferenceAndChapter")
    public  List<ChemFixedAnswerTaskDto> findTaskByReferenceIdAndChapterId(int referenceId, int chapterId){
        return presentation.getAllChemistryFixedAnswerByReferenceIdAndChapterIdDto(referenceId, chapterId);
    }
}
