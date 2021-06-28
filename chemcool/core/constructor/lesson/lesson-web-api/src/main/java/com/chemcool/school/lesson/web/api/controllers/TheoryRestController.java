package com.chemcool.school.lesson.web.api.controllers;

import com.chemcool.school.lesson.theory.domain.ChemTheory;
import com.chemcool.school.lesson.web.api.dto.ChemTheoryDto;
import com.chemcool.school.lesson.web.api.service.ChemTheoryPresentation;
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
public class TheoryRestController {
    private final ChemTheoryPresentation presentation;

    @ApiOperation("Возвращает сущности теории по главе")
    @GetMapping("/findTheoryByChapterId")
    public List<ChemTheoryDto> findTheoryByChapter(int chapterId){
        return presentation.getAllTheoryByChapterIdDto(chapterId);
    }

    @ApiOperation("Возвращает сущности теории по разделу")
    @GetMapping("/findTheoryByReferenceId")
    public List<ChemTheoryDto> findTheoryByReferenceId(int referenceId){
        return presentation.getAllTheoryByReferenceIdDto(referenceId);
    }

    @ApiOperation("Возвращает сущности теории по разделу и главе")
    @GetMapping("/findTheoryByReferenceIdAndChapterId")
    public List<ChemTheoryDto> findTheoryByReferenceIdAndChapterId(int referenceId, int chapterId){
        return presentation.getAllTheoryByReferenceIdAndChapterIdDto(referenceId, chapterId);
    }
}
