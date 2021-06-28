package com.chemcool.school.lesson.web.api.controllers;


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
public class LessonRestController {

    private final TheoryRestController theoryRestController;
    private final ChemEquationsRestController chemEquationsRestController;
    private final ChemFixedAnswerRestController chemFixedAnswerRestController;
    private final ChemMatchesRestController matchesRestController;
    private final ChemSingleSelectRestController chemSingleSelectRestController;


    @ApiOperation("Возвращает сущности заданий и теории по разделу")
    @GetMapping("/getLessonByReferenceId")
    @ResponseStatus(HttpStatus.OK)
    public List[] findLessonByReferenceId(int referenceId) {
        return new List[]{
                theoryRestController.findTheoryByReferenceId(referenceId),
                chemEquationsRestController.findTaskByReferenceId(referenceId),
                chemFixedAnswerRestController.findTaskByReferenceId(referenceId),
                matchesRestController.findTaskByReferences(referenceId),
                chemSingleSelectRestController.findTaskByReferenceId(referenceId)
        };
    }

    @ApiOperation("Возвращает сущности заданий и теории по главе")
    @GetMapping("/getLessonByChapterId")
    @ResponseStatus(HttpStatus.OK)
    public List[] findLessonByChapterId(int chapterId) {
        return new List[]{
                theoryRestController.findTheoryByChapter(chapterId),
                chemEquationsRestController.findTaskByChapterId(chapterId),
                chemFixedAnswerRestController.findTaskByChapterId(chapterId),
                matchesRestController.findTaskByChapter(chapterId),
                chemSingleSelectRestController.findTaskByChapterId(chapterId)
        };
    }

    @ApiOperation("Возвращает сущности заданий и теории по разделу и главе")
    @GetMapping("/getLessonByReferenceIdAndChapterId")
    @ResponseStatus(HttpStatus.OK)
    public List[] findLessonReferenceIdAndByChapterId(int chapterId, int referenceId) {
        return new List[]{
                theoryRestController.findTheoryByReferenceIdAndChapterId(chapterId, referenceId),
                chemEquationsRestController.findTaskByReferenceIdAndChapterId(chapterId, referenceId),
                chemFixedAnswerRestController.findTaskByReferenceIdAndChapterId(chapterId, referenceId),
                matchesRestController.findTaskByReferenceIdAndChapterId(chapterId, referenceId),
                chemSingleSelectRestController.findTaskByReferenceIdAndChapterId(chapterId, referenceId)
        };
    }
}
