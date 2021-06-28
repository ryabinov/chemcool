package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.web.api.dto.ChemFixedAnswerTaskDto;
import com.chemcool.school.lesson.web.api.exeption.ChemTaskEmptyException;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class ChemFixedAnswerTaskPresentationTest {

    @MockBean
    private ChemFixedAnswerTaskServiceLayer serviceLayer;

    @Autowired
    private ChemFixedAnswerTaskPresentation presentation;

    private final List<ChemFixedAnswerTaskDto> taskList = new ArrayList<>();

    private String id;
    private Integer i, referenceId, chapterId;

    @BeforeEach
    void setUp() {
        taskList.add(new ChemFixedAnswerTaskDto(
                "id1",
                "description1",
                "rightAnswer",
                1,
                1,
                "FixedAnswer"
        ));
        taskList.add(new ChemFixedAnswerTaskDto(
                "id2",
                "description2",
                "rightAnswer",
                1,
                2,
                "FixedAnswer"
        ));
        taskList.add(new ChemFixedAnswerTaskDto(
                "id3",
                "description3",
                "rightAnswer",
                2,
                2,
                "FixedAnswer"
        ));
        i = 0;
        id = taskList.get(i).getTaskId();
        referenceId = 2;
        chapterId = 1;
    }

    @Test
    void getAllChemistryFixedAnswerDto() {
        Mockito.when(serviceLayer.getAllChemistryFixedAnswerDto()).thenReturn(taskList);
        List<ChemFixedAnswerTaskDto> taskExamples = presentation.getAllChemistryFixedAnswerDto();
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemFixedAnswerTaskDto task : taskExamples) {
            assertThat(task.getTaskId()).isNotNull();
            assertThat(task.getChapterId()).isNotNull();
            assertThat(task.getReferenceId()).isNotNull();
            assertThat(task.getTaskType()).isEqualTo("FixedAnswer");
        }
    }

    @Test
    void getFixedAnswerTaskById() {
        Mockito.when(serviceLayer.getFixedAnswerTaskById(id)).thenReturn(taskList.get(i));
        ChemFixedAnswerTaskDto task = presentation.getFixedAnswerTaskById(id);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("description" + (i + 1));
        assertThat(task.getTaskId()).isEqualTo(id);
            assertThat(task.getTaskType()).isEqualTo("FixedAnswer");
    }

    @Test
    void getFixedAnswerTaskByFakeId() throws ChemTaskEmptyException {
        Mockito.when(serviceLayer.getFixedAnswerTaskById(id)).thenReturn(taskList.get(0));
        ChemFixedAnswerTaskDto task = presentation.getFixedAnswerTaskById(id + "1");
        System.out.println(task);
        assertThat(task).isNull();
    }

    @Test
    void getAllChemistryFixedAnswerByReferenceIdDto() {
        Mockito.when(serviceLayer.getAllChemistryFixedAnswerByReferenceIdDto(referenceId)).thenReturn(taskList.subList(1, 3));
        List<ChemFixedAnswerTaskDto> taskExamples = presentation.getAllChemistryFixedAnswerByReferenceIdDto(referenceId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemFixedAnswerTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getTaskType()).isEqualTo("FixedAnswer");
        }
    }

    @Test
    void getAllChemistryFixedAnswerByChapterIdDto() {
        Mockito.when(serviceLayer.getAllChemistryFixedAnswerByChapterIdDto(chapterId)).thenReturn(taskList.subList(0, 2));
        List<ChemFixedAnswerTaskDto> taskExamples = presentation.getAllChemistryFixedAnswerByChapterIdDto(chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemFixedAnswerTaskDto task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("FixedAnswer");
        }
    }

    @Test
    void getAllChemistryFixedAnswerByReferenceIdAndChapterIdDto() {
        Mockito.when(serviceLayer.getAllChemistryFixedAnswerByReferenceIdAndChapterIdDto(referenceId, chapterId)).thenReturn(taskList.subList(1, 2));
        List<ChemFixedAnswerTaskDto> taskExamples = presentation.getAllChemistryFixedAnswerByReferenceIdAndChapterIdDto(referenceId, chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(1);
        for (ChemFixedAnswerTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("FixedAnswer");
        }
    }
}