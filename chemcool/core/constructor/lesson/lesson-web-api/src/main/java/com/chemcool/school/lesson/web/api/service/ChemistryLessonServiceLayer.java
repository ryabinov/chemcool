package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.web.api.dto.LessonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChemistryLessonServiceLayer {

    private final ChemistryLessonProxyService service;

    public String add(LessonDto dto){
        return service.add(new ChemistryLessonExample(
                dto.getLessonName(),
                dto.getLessonDescription(),
                dto.getLessonChapter(),
                dto.getLessonReferences()
        ));
    }

    public ChemistryLesson findTaskAndTheoryByChapter(int chapter){
        return service.findTaskAndTheoryByChapter(chapter);
    }

    public ChemistryLesson findTaskAndTheoryByReferences(int references){
        return service.findTaskAndTheoryByReferences(references);
    }
}
