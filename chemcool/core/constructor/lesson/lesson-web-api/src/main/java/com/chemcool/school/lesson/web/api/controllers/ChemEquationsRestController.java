package com.chemcool.school.lesson.web.api.controllers;

import com.chemcool.school.lesson.web.api.dto.ChemEquationsTaskDto;
import com.chemcool.school.lesson.web.api.service.ChemEquationsTaskPresentation;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping({"/v1.0"})
@AllArgsConstructor
public class ChemEquationsRestController {

    private final ChemEquationsTaskPresentation presentation;

    @ApiOperation("Возвращает сущности задания типа \"Уравнения\" по разделу")
    @GetMapping("/findEquationsTaskByReferenceId")
    @ResponseStatus(HttpStatus.OK)
    public List<ChemEquationsTaskDto> findTaskByReferenceId(int referenceId) {
        return presentation.getAllChemistryEquationsByReferenceIdDto(referenceId);
    }

    @ApiOperation("Возвращает сущности задания типа \"Уравнения\" по главе")
    @GetMapping("/findEquationsTaskByChapterId")
    @ResponseStatus(HttpStatus.OK)
    public List<ChemEquationsTaskDto> findTaskByChapterId(int chapterId) {
        return presentation.getAllChemistryEquationsByChapterIdDto(chapterId);
    }

    @ApiOperation("Возвращает сущности задания типа \"Уравнения\" по разделу и главе")
    @GetMapping("/findEquationsTaskByReferenceIdAndChapterId")
    @ResponseStatus(HttpStatus.OK)
    public List<ChemEquationsTaskDto> findTaskByReferenceIdAndChapterId(int referenceId, int chapterId) {
        return presentation.getAllChemistryEquationsByReferenceIdAndChapterIdDto(referenceId, chapterId);
    }
}
