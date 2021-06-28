package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.web.api.dto.ChemFixedAnswerTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChemFixedAnswerTaskPresentation {


    private final ChemFixedAnswerTaskServiceLayer serviceLayer;

    public List<ChemFixedAnswerTaskDto> getAllChemistryFixedAnswerDto(){
        return serviceLayer.getAllChemistryFixedAnswerDto();
    }

    public ChemFixedAnswerTaskDto getFixedAnswerTaskById(String id){
        return serviceLayer.getFixedAnswerTaskById(id);
    }

    public  List<ChemFixedAnswerTaskDto> getAllChemistryFixedAnswerByReferenceIdDto(int referenceId){
        return serviceLayer.getAllChemistryFixedAnswerByReferenceIdDto(referenceId);
    }

    public  List<ChemFixedAnswerTaskDto> getAllChemistryFixedAnswerByChapterIdDto(int chapterId){
        return serviceLayer.getAllChemistryFixedAnswerByChapterIdDto(chapterId);
    }

    public  List<ChemFixedAnswerTaskDto> getAllChemistryFixedAnswerByReferenceIdAndChapterIdDto(int referenceId, int chapterId){
        return serviceLayer.getAllChemistryFixedAnswerByReferenceIdAndChapterIdDto(referenceId, chapterId);
    }
}
