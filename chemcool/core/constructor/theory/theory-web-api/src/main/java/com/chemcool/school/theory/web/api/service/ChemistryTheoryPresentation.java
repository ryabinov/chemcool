package com.chemcool.school.theory.web.api.service;

import com.chemcool.school.theory.domain.ChemistryTheory;
import com.chemcool.school.theory.web.api.dto.TheoryDto;
import com.chemcool.school.theory.web.api.exception.ChemistryTheoryEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChemistryTheoryPresentation {

    private final ChemistryTheoryServiceLayer serviceLayer;

    public String createChemistryTheoryDto(TheoryDto dto) {
        if (
                dto.getTheoryName().isEmpty()
        ) {
            throw new ChemistryTheoryEmptyException("Необходимые поля пустые, проверьте пожалуйста бланк заполнения темы.");
        } else {
            return serviceLayer.add(dto);
        }
    }

    public void deleteChemistryTheoryDto(TheoryDto dto) {
        serviceLayer.delete(dto);
    }

    public void updateChemistryTheoryDto(TheoryDto dto) {
        serviceLayer.update(dto);
    }

    public ChemistryTheory getTheoryById(String theoryId) {
        return serviceLayer.findTheoryById(theoryId);
    }
}
