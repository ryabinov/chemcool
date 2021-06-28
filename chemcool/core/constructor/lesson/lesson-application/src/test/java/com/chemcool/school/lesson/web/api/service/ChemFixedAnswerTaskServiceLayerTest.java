package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.fixedanswer.ChemFixedAnswerTask;
import com.chemcool.school.lesson.domain.fixedanswer.ChemFixedAnswerTaskExample;
import com.chemcool.school.lesson.service.fixedanswer.ChemFixedAnswerTaskProxyService;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class ChemFixedAnswerTaskServiceLayerTest {

    @MockBean
    private ChemFixedAnswerTaskProxyService proxyService;

    @Autowired
    private ChemFixedAnswerTaskServiceLayer serviceLayer;

    private final List<ChemFixedAnswerTask> taskList = new ArrayList<>();

    private String id;
    private Integer i, referenceId, chapterId;

    @BeforeEach
    void setUp() {
        taskList.add(ChemFixedAnswerTask.createChemistryFixedAnswerTask(new ChemFixedAnswerTaskExample(
                "description",
                "rightAnswer",
                1,
                1
        )));
        taskList.add(ChemFixedAnswerTask.createChemistryFixedAnswerTask(new ChemFixedAnswerTaskExample(
                "another_description",
                "another_rightAnswer",
                1,
                2
        )));
        taskList.add(ChemFixedAnswerTask.createChemistryFixedAnswerTask(new ChemFixedAnswerTaskExample(
                "another_description",
                "another_rightAnswer",
                2,
                2
        )));
        i = 0; //Элемент листа, при значения !=0 тест getById должен падать
        id = taskList.get(i).getTaskId();
        referenceId = 2;
        chapterId = 1;
    }

    @Test
    void getAllChemistryFixedAnswerDto() {
        Mockito.when(proxyService.getAll()).thenReturn(taskList);
        List<ChemFixedAnswerTaskDto> taskExamples = serviceLayer.getAllChemistryFixedAnswerDto();
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemFixedAnswerTaskDto task : taskExamples) {
            assertThat(task.getTaskId()).isNotNull();
            assertThat(task.getChapterId()).isNotNull();
            assertThat(task.getReferenceId()).isNotNull();
        }
    }

    @Test
    void getAllChemistryFixedAnswerByReferenceIdDto() {
        Mockito.when(proxyService.getAllByReferenceId(referenceId)).thenReturn(taskList.subList(1, 3));
        List<ChemFixedAnswerTaskDto> taskExamples = serviceLayer.getAllChemistryFixedAnswerByReferenceIdDto(referenceId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemFixedAnswerTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
        }
    }

    @Test
    void getAllChemistryFixedAnswerByChapterIdDto() {
        Mockito.when(proxyService.getAllByChapterId(chapterId)).thenReturn(taskList.subList(0, 2));
        List<ChemFixedAnswerTaskDto> taskExamples = serviceLayer.getAllChemistryFixedAnswerByChapterIdDto(chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemFixedAnswerTaskDto task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }

    @Test
    void getAllChemistryFixedAnswerByReferenceIdAndChapterIdDto() {
        Mockito.when(proxyService.getAllByReferenceIdAndChapterId(referenceId, chapterId)).thenReturn(taskList.subList(1, 2));
        List<ChemFixedAnswerTaskDto> taskExamples = serviceLayer.getAllChemistryFixedAnswerByReferenceIdAndChapterIdDto(referenceId, chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(1);
        for (ChemFixedAnswerTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }

    @Test
    void getFixedAnswerTaskById() {
        Mockito.when(proxyService.getById(id)).thenReturn(Optional.ofNullable(taskList.get(i)));
        ChemFixedAnswerTaskDto task = serviceLayer.getFixedAnswerTaskById(id);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("description");
        assertThat(task.getTaskId()).isEqualTo(id);
    }
    @Test
    void getFixedAnswerTaskByFakeId() throws ChemTaskEmptyException {
        Mockito.when(proxyService.getById(id)).thenReturn(Optional.ofNullable(taskList.get(0)));
        Exception exception = assertThrows(ChemTaskEmptyException.class,() -> {
            ChemFixedAnswerTaskDto task = serviceLayer.getFixedAnswerTaskById(id + "1");
        });
        String expectedMessage = "Задание не найдено.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}