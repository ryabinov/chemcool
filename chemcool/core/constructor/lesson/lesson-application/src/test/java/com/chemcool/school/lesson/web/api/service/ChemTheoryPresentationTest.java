package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.theory.domain.ChemTheory;
import com.chemcool.school.lesson.theory.domain.ChemTheoryExample;
import com.chemcool.school.lesson.web.api.dto.ChemTheoryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class ChemTheoryPresentationTest {

    @MockBean
    private ChemTheoryServiceLayer serviceLayer;

    @Autowired
    private ChemTheoryPresentation presentation;

    private final List<ChemTheoryDto> theoryList = new ArrayList<>();

    private String id;
    private Integer i, referenceId, chapterId;

    @BeforeEach
    void setUp() {
        theoryList.add(new ChemTheoryDto(
                "id1",
                "theoryExampleName1",
                "theoryExampleDescription1",
                1,
                1,
                "Theory"
        ));
        theoryList.add(new ChemTheoryDto(
                "id2",
                "theoryExampleName2",
                "theoryExampleDescription2",
                1,
                2,
                "Theory"
        ));
        theoryList.add(new ChemTheoryDto(
                "id3",
                "theoryExampleName3",
                "theoryExampleDescription3",
                2,
                2,
                "Theory"
        ));
        i = 0;
        id = theoryList.get(i).getTheoryDtoId();
        referenceId = 2;
        chapterId = 1;
    }

    @Test
    void getTheoryByIdDto() {
        Mockito.when(serviceLayer.getTheoryByIdDto(id)).thenReturn(theoryList.get(i));
        ChemTheoryDto theory = presentation.getTheoryByIdDto(id);
        assertThat(theory).isNotNull();
        assertThat(theory.getTheoryDescription()).isEqualTo("theoryExampleDescription"+(i+1));
        assertThat(theory.getTheoryDtoId()).isEqualTo(id);
        assertThat(theory.getTaskType()).isEqualTo("Theory");
    }

    @Test
    void getTheoryByFakeIdDto() {
        Mockito.when(serviceLayer.getTheoryByIdDto(id)).thenReturn(theoryList.get(0));
        ChemTheoryDto theory = presentation.getTheoryByIdDto(id+"1");
        assertThat(theory).isNull();
    }

    @Test
    void getAllTheoryDto() {
        Mockito.when(serviceLayer.getAllTheoryDto()).thenReturn(theoryList);
        List<ChemTheoryDto> theoryExamples = presentation.getAllTheoryDto();
        assertThat(theoryExamples).isNotNull();
        assertThat(theoryExamples).hasSize(3);
        for (ChemTheoryDto task : theoryExamples) {
            assertThat(task.getTheoryDtoId()).isNotNull();
            assertThat(task.getTheoryChapter()).isNotNull();
            assertThat(task.getTheoryReferences()).isNotNull();
            assertThat(task.getTaskType()).isEqualTo("Theory");
        }
    }

    @Test
    void getAllTheoryByReferenceIdDto() {
        Mockito.when(serviceLayer.getAllTheoryByReferenceIdDto(referenceId)).thenReturn(theoryList.subList(1, 3));
        List<ChemTheoryDto> theoryExamples = presentation.getAllTheoryByReferenceIdDto(referenceId);
        assertThat(theoryExamples).isNotNull();
        assertThat(theoryExamples).hasSize(2);
        for (ChemTheoryDto theoryDto : theoryExamples) {
            assertThat(theoryDto.getTheoryReferences()).isEqualTo(referenceId);
            assertThat(theoryDto.getTaskType()).isEqualTo("Theory");
        }
    }

    @Test
    void getAllTheoryByChapterIdDto() {
        Mockito.when(serviceLayer.getAllTheoryByChapterIdDto(chapterId)).thenReturn(theoryList.subList(0, 2));
        List<ChemTheoryDto> theoryExamples = presentation.getAllTheoryByChapterIdDto(chapterId);
        assertThat(theoryExamples).isNotNull();
        assertThat(theoryExamples).hasSize(2);
        for (ChemTheoryDto theoryDto : theoryExamples) {
            assertThat(theoryDto.getTheoryChapter()).isEqualTo(chapterId);
            assertThat(theoryDto.getTaskType()).isEqualTo("Theory");
        }
    }

    @Test
    void getAllTheoryByReferenceIdAndChapterIdDto() {
        Mockito.when(serviceLayer.getAllTheoryByReferenceIdIndChapterIdDto(referenceId, chapterId)).thenReturn(theoryList.subList(1, 2));
        List<ChemTheoryDto> theoryExamples = presentation.getAllTheoryByReferenceIdAndChapterIdDto(referenceId,chapterId);
        assertThat(theoryExamples).isNotNull();
        assertThat(theoryExamples).hasSize(1);
        for (ChemTheoryDto theoryDto : theoryExamples) {
            assertThat(theoryDto.getTheoryReferences()).isEqualTo(referenceId);
            assertThat(theoryDto.getTheoryChapter()).isEqualTo(chapterId);
            assertThat(theoryDto.getTaskType()).isEqualTo("Theory");
        }
    }
}