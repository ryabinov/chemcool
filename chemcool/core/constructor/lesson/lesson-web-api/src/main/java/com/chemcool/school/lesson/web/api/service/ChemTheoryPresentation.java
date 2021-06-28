package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.web.api.dto.ChemTheoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChemTheoryPresentation {

    private final ChemTheoryServiceLayer serviceLayer;

    public ChemTheoryDto getTheoryByIdDto(String theoryId) {
        return serviceLayer.getTheoryByIdDto(theoryId);
    }

    public List<ChemTheoryDto> getAllTheoryDto(){
        return serviceLayer.getAllTheoryDto();
    }

    public List<ChemTheoryDto> getAllTheoryByReferenceIdDto(int referenceId){
        return serviceLayer.getAllTheoryByReferenceIdDto(referenceId);
    }

    public List<ChemTheoryDto> getAllTheoryByChapterIdDto(int chapterId){
        return serviceLayer.getAllTheoryByChapterIdDto(chapterId);
    }

    public List<ChemTheoryDto> getAllTheoryByReferenceIdAndChapterIdDto(int referenceId, int chapterId){
        return serviceLayer.getAllTheoryByReferenceIdIndChapterIdDto(referenceId, chapterId);
    }
}
