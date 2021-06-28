package com.chemcool.school.lesson.web.api.service;


import com.chemcool.school.lesson.web.api.dto.ChemAnswerDto;
import com.chemcool.school.lesson.web.api.dto.ChemEquationsTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ChemEquationsTaskPresentation {

    private final ChemEquationsTaskServiceLayer serviceLayer;

    public List<ChemEquationsTaskDto> getAllChemistryEquationsDto() {
        return serviceLayer.getAllChemEquationsDto();
    }

    public List<ChemEquationsTaskDto> getAllChemistryEquationsByReferenceIdDto(int referenceId) {
        return serviceLayer.getAllChemEquationsByReferenceIdDto(referenceId);
    }

    public List<ChemEquationsTaskDto> getAllChemistryEquationsByChapterIdDto(int chapterId) {
        return serviceLayer.getAllChemEquationsByChapterIdDto(chapterId);
    }
    public List<ChemEquationsTaskDto> getAllChemistryEquationsByReferenceIdAndChapterIdDto(int referenceId,int chapterId) {
        return serviceLayer.getAllByReferenceIdAndChapterIdDto(referenceId,chapterId);
    }

    public ChemEquationsTaskDto getEquationsTaskById(String id) {
        return serviceLayer.getChemEquationsTaskById(id);
    }

}