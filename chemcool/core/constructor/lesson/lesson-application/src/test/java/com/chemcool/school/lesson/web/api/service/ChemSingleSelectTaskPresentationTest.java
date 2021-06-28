package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.web.api.dto.ChemSingleSelectTaskDto;
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
class ChemSingleSelectTaskPresentationTest {

    @MockBean
    private ChemSingleSelectTaskServiceLayer serviceLayer;

    @Autowired
    ChemSingleSelectTaskPresentation presentation;

    private final List<ChemSingleSelectTaskDto> taskList = new ArrayList<>();

    private String id;
    private Integer i, referenceId, chapterId;

    @BeforeEach
    void setUp() {
        taskList.add(new ChemSingleSelectTaskDto(
                "id1",
                "description1",
                "correctAnswer1",
                1,
                1,
                "IncorrectAnswerOne",
                "IncorrectAnswerTwo",
                "IncorrectAnswerThree",
                "IncorrectAnswerFour",
                "SingleSelect"
        ));
        taskList.add(new ChemSingleSelectTaskDto(
                "id2",
                "description2",
                "correctAnswer2",
                1,
                2,
                "IncorrectAnswerOne",
                "IncorrectAnswerTwo",
                "IncorrectAnswerThree",
                "IncorrectAnswerFour",
                "SingleSelect"
        ));
        taskList.add(new ChemSingleSelectTaskDto(
                "id3",
                "description3",
                "correctAnswer3",
                2,
                2,
                "IncorrectAnswerOne",
                "IncorrectAnswerTwo",
                "IncorrectAnswerThree",
                "IncorrectAnswerFour",
                "SingleSelect"
        ));
        i = 0;
        id = taskList.get(i).getTaskDtoId();
        referenceId = 2;
        chapterId = 1;
    }

    @Test
    void getTaskDtoByIdDto() {
        Mockito.when(serviceLayer.getTaskDtoByIdDto(id)).thenReturn(taskList.get(i));
        ChemSingleSelectTaskDto task = presentation.getTaskDtoByIdDto(id);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("description" + (i + 1));
        assertThat(task.getTaskDtoId()).isEqualTo(id);
        assertThat(task.getTaskType()).isEqualTo("SingleSelect");
    }

    @Test
    void getTaskDtoByFakeIdDto() {
        Mockito.when(serviceLayer.getTaskDtoByIdDto(id)).thenReturn(taskList.get(0));
        ChemSingleSelectTaskDto task = presentation.getTaskDtoByIdDto(id+"1");
        assertThat(task).isNull();
    }

    @Test
    void getAllTasksDto() {
        Mockito.when(serviceLayer.getAllTasksDto()).thenReturn(taskList);
        List<ChemSingleSelectTaskDto> taskExamples = presentation.getAllTasksDto();
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemSingleSelectTaskDto task : taskExamples) {
            assertThat(task.getTaskDtoId()).isNotNull();
            assertThat(task.getChapterId()).isNotNull();
            assertThat(task.getReferenceId()).isNotNull();
            assertThat(task.getTaskType()).isEqualTo("SingleSelect");
        }
    }

    @Test
    void getAllTasksByReferenceIdDto() {
        Mockito.when(serviceLayer.getAllTasksByReferenceIdDto(referenceId)).thenReturn(taskList.subList(1, 3));
        List<ChemSingleSelectTaskDto> taskExamples = presentation.getAllTasksByReferenceIdDto(referenceId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemSingleSelectTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getTaskType()).isEqualTo("SingleSelect");
        }
    }

    @Test
    void getAllTasksByChapterIdDto() {
        Mockito.when(serviceLayer.getAllTasksByChapterIdDto(chapterId)).thenReturn(taskList.subList(0, 2));
        List<ChemSingleSelectTaskDto> taskExamples = presentation.getAllTasksByChapterIdDto(chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemSingleSelectTaskDto task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("SingleSelect");
        }
    }

    @Test
    void getAllTasksByReferenceIdAndChapterIdDto() {
        Mockito.when(serviceLayer.getAllTasksByReferenceIdAndChapterIdDto(referenceId, chapterId)).thenReturn(taskList.subList(1, 2));
        List<ChemSingleSelectTaskDto> taskExamples = presentation.getAllTasksByReferenceIdAndChapterIdDto(referenceId, chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(1);
        for (ChemSingleSelectTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("SingleSelect");
        }
    }
}