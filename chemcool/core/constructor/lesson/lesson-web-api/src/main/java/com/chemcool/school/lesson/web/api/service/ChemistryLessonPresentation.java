package com.chemcool.school.lesson.web.api.service;


import com.chemcool.school.lesson.web.api.dto.LessonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChemistryLessonPresentation {

    private final ChemistryLessonServiceLayer serviceLayer;

    public String add(LessonDto dto){
        return serviceLayer.add(dto);
    }

    public ChemistryLesson findTaskAndTheoryByChapter(int chapter){
        return serviceLayer.findTaskAndTheoryByChapter(chapter);
    }

    public ChemistryLesson findTaskAndTheoryByReferences(int references){
        return serviceLayer.findTaskAndTheoryByReferences(references);
    }
}
