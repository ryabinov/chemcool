package com.chemcool.school.theory.web.api.service;

import com.chemcool.school.theory.domain.ChemistryTheory;
import com.chemcool.school.theory.domain.ChemistryTheoryExample;
import com.chemcool.school.theory.service.ChemistryTheoryProxyService;
import com.chemcool.school.theory.web.api.dto.TheoryDto;
import com.chemcool.school.theory.web.api.exception.ChemistryTheoryEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChemistryTheoryServiceLayer {

    private final ChemistryTheoryProxyService service;

    public String add(TheoryDto dto) {
        validate(dto);
        return service.add(new ChemistryTheoryExample(
                dto.getTheoryName(),
                dto.getTheoryDescription(),
                dto.getTheoryChapter(),
                dto.getTheoryReferences()
                )
        );
    }
    public void delete(TheoryDto dto){
        service.delete(new ChemistryTheory(
                dto.getTheoryDtoId(),
                dto.getTheoryName(),
                dto.getTheoryDescription(),
                dto.getTheoryChapter(),
                dto.getTheoryReferences()
        ));
    }

    public void update(TheoryDto dto){
        validate(dto);
        service.update(
                new ChemistryTheory(
                        dto.getTheoryDtoId(),
                        dto.getTheoryName(),
                        dto.getTheoryDescription(),
                        dto.getTheoryChapter(),
                        dto.getTheoryReferences()
                )
        );
    }
    public ChemistryTheory findTheoryById(String theoryId){
        return service.findTheoryById(theoryId);
    }

    private void validate(TheoryDto dto) {
        if (dto.getTheoryDescription().isEmpty()) {
            throw new ChemistryTheoryEmptyException("Необходимые поля пустые, проверьте пожалуйста бланк заполнения задания.");
        }
    }

}
