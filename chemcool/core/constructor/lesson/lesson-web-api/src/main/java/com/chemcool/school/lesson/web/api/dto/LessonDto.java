package com.chemcool.school.lesson.web.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {
    private String lessonDtoId;
    private String lessonName;
    private String lessonDescription;
    private Integer lessonChapter;
    private Integer lessonReferences;

    public LessonDto(ChemistryLesson lesson){
        this.lessonDtoId =lesson.getLessonId();
        this.lessonName =lesson.getLessonName();
        this.lessonDescription =lesson.getLessonDescription();
        this.lessonChapter=lesson.getLessonChapter();
        this.lessonReferences=lesson.getLessonReferences();

    }


}
