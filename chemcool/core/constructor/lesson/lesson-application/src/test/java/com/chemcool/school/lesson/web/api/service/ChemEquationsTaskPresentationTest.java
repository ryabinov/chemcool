package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.web.api.dto.ChemEquationsTaskDto;
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
class ChemEquationsTaskPresentationTest {

    @MockBean
    private ChemEquationsTaskServiceLayer serviceLayer;

    @Autowired
    private ChemEquationsTaskPresentation presentation;

    private final List<ChemEquationsTaskDto> taskList = new ArrayList<>();

    private String id;
    private Integer i, referenceId, chapterId;

    @BeforeEach
    void setUp() {
        taskList.add(new ChemEquationsTaskDto(
                "Id1",
                "description1",
                1,
                1,
                "Equations"
        ));
        taskList.add(new ChemEquationsTaskDto(
                "Id2",
                "description2",
                1,
                2,
                "Equations"
        ));
        taskList.add(new ChemEquationsTaskDto(
                "Id3",
                "description3",
                2,
                2,
                "Equations"
        ));
        i = 0;
        id = taskList.get(i).getTaskId();
        referenceId = 2;
        chapterId = 1;
    }

    @Test
    void getAllChemistryEquationsDto() {
        Mockito.when(serviceLayer.getAllChemEquationsDto()).thenReturn(taskList);
        List<ChemEquationsTaskDto> taskExamples = presentation.getAllChemistryEquationsDto();
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemEquationsTaskDto task : taskExamples) {
            assertThat(task.getTaskId()).isNotNull();
            assertThat(task.getChapterId()).isNotNull();
            assertThat(task.getReferenceId()).isNotNull();
            assertThat(task.getTaskType()).isEqualTo("Equations");
        }
    }

    @Test
    void getAllChemistryEquationsByReferenceIdDto() {
        Mockito.when(serviceLayer.getAllChemEquationsByReferenceIdDto(referenceId)).thenReturn(taskList.subList(1, 3));
        List<ChemEquationsTaskDto> taskExamples = presentation.getAllChemistryEquationsByReferenceIdDto(referenceId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemEquationsTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getTaskType()).isEqualTo("Equations");
        }
    }

    @Test
    void getAllChemistryEquationsByChapterIdDto() {
        Mockito.when(serviceLayer.getAllChemEquationsByChapterIdDto(chapterId)).thenReturn(taskList.subList(0, 2));
        List<ChemEquationsTaskDto> taskExamples = presentation.getAllChemistryEquationsByChapterIdDto(chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemEquationsTaskDto task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("Equations");
        }
    }

    @Test
    void getAllChemistryEquationsByReferenceIdAndChapterIdDto() {
        Mockito.when(serviceLayer.getAllByReferenceIdAndChapterIdDto(referenceId, chapterId)).thenReturn(taskList.subList(1, 2));
        List<ChemEquationsTaskDto> taskExamples = presentation.getAllChemistryEquationsByReferenceIdAndChapterIdDto(referenceId, chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(1);
        for (ChemEquationsTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("Equations");
        }
    }

    @Test
    void getEquationsTaskById() {
        Mockito.when(serviceLayer.getChemEquationsTaskById(id)).thenReturn(taskList.get(i));
        ChemEquationsTaskDto task = presentation.getEquationsTaskById(id);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("description" + (i + 1));
        assertThat(task.getTaskId()).isEqualTo(id);
        assertThat(task.getTaskType()).isEqualTo("Equations");
    }

    @Test
    void getChemEquationsTaskByFakeId() throws ChemTaskEmptyException {
        Mockito.when(serviceLayer.getChemEquationsTaskById(id)).thenReturn(taskList.get(0));
        ChemEquationsTaskDto task = presentation.getEquationsTaskById(id + "1");
        System.out.println(task);
        assertThat(task).isNull();
    }
}