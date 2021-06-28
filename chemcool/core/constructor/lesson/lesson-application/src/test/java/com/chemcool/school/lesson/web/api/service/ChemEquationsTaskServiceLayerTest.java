package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.equation.ChemEquationsTask;
import com.chemcool.school.lesson.domain.equation.ChemEquationsTaskExample;
import com.chemcool.school.lesson.service.equation.ChemEquationsTaskProxyService;
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

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class ChemEquationsTaskServiceLayerTest {

    @MockBean
    private ChemEquationsTaskProxyService proxyService;

    @Autowired
    private ChemEquationsTaskServiceLayer serviceLayer;

    private final List<ChemEquationsTask> taskList = new ArrayList<>();

    private String id;
    private Integer i, referenceId, chapterId;

    @BeforeEach
    void setUp() {
        taskList.add(ChemEquationsTask.createChemEquationsTask(new ChemEquationsTaskExample(
                "description",
                "rightAnswer",
                1,
                1
        )));
        taskList.add(ChemEquationsTask.createChemEquationsTask(new ChemEquationsTaskExample(
                "another_description",
                "another_rightAnswer",
                1,
                2
        )));
        taskList.add(ChemEquationsTask.createChemEquationsTask(new ChemEquationsTaskExample(
                "another_description",
                "another_rightAnswer",
                2,
                2
        )));
        i = 0;
        id = taskList.get(i).getTaskId();
        referenceId = 2;
        chapterId = 1;
    }

    @Test
    void getAllChemEquationsDto() {
        Mockito.when(proxyService.getAll()).thenReturn(taskList);
        List<ChemEquationsTaskDto> taskExamples = serviceLayer.getAllChemEquationsDto();
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
    void getAllChemEquationsByChapterIdDto() {
        Mockito.when(proxyService.getAllByChapterId(chapterId)).thenReturn(taskList.subList(0, 2));
        List<ChemEquationsTaskDto> taskExamples = serviceLayer.getAllChemEquationsByChapterIdDto(chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemEquationsTaskDto task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("Equations");
        }
    }

    @Test
    void getAllChemEquationsByReferenceIdDto() {
        Mockito.when(proxyService.getAllByReferenceId(referenceId)).thenReturn(taskList.subList(1, 3));
        List<ChemEquationsTaskDto> taskExamples = serviceLayer.getAllChemEquationsByReferenceIdDto(referenceId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemEquationsTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getTaskType()).isEqualTo("Equations");
        }
    }

    @Test
    void getAllByReferenceIdAndChapterIdDto() {
        Mockito.when(proxyService.getAllByReferenceIdAndChapterId(referenceId, chapterId)).thenReturn(taskList.subList(1, 2));
        List<ChemEquationsTaskDto> taskExamples = serviceLayer.getAllByReferenceIdAndChapterIdDto(referenceId, chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(1);
        for (ChemEquationsTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("Equations");
        }
    }

    @Test
    void getChemEquationsTaskByIdDto() {
        Mockito.when(proxyService.getById(id)).thenReturn(Optional.ofNullable(taskList.get(i)));
        ChemEquationsTaskDto task = serviceLayer.getChemEquationsTaskById(id);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("description");
        assertThat(task.getTaskId()).isEqualTo(id);
        assertThat(task.getTaskType()).isEqualTo("Equations");
    }

    @Test
    void getChemEquationsTaskByFakeIdDto() throws ChemTaskEmptyException {
        Mockito.when(proxyService.getById(id)).thenReturn(Optional.ofNullable(taskList.get(0)));
        Exception exception = assertThrows(ChemTaskEmptyException.class,() -> {
            ChemEquationsTaskDto task = serviceLayer.getChemEquationsTaskById(id + "1");
        });
        String expectedMessage = "Задание не найдено.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}