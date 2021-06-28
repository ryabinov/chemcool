package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.singleselect.ChemSingleSelectTask;
import com.chemcool.school.lesson.domain.singleselect.ChemSingleSelectTaskExample;
import com.chemcool.school.lesson.service.singleselect.ChemSingleSelectTaskProxyService;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class ChemSingleSelectTaskServiceLayerTest {

    @MockBean
    private ChemSingleSelectTaskProxyService proxyService;

    @Autowired
    private ChemSingleSelectTaskServiceLayer serviceLayer;

    private final List<ChemSingleSelectTask> taskList = new ArrayList<>();

    private String id;
    private Integer i, referenceId, chapterId;

    @BeforeEach
    void setUp() {
        taskList.add(ChemSingleSelectTask.createChemistrySingleSelectTask(new ChemSingleSelectTaskExample(
                "description",
                "correctAnswer",
                1,
                1,
                "IncorrectAnswerOne",
                "IncorrectAnswerTwo",
                "IncorrectAnswerThree",
                "IncorrectAnswerFour"
        )));
        taskList.add(ChemSingleSelectTask.createChemistrySingleSelectTask(new ChemSingleSelectTaskExample(
                "another_description",
                "another_correctAnswer",
                1,
                2,
                "another_IncorrectAnswerOne",
                "another_IncorrectAnswerTwo",
                "another_IncorrectAnswerThree",
                "another_IncorrectAnswerFour"
        )));
        taskList.add(ChemSingleSelectTask.createChemistrySingleSelectTask(new ChemSingleSelectTaskExample(
                "another_description",
                "another_correctAnswer",
                2,
                2,
                "another_IncorrectAnswerOne",
                "another_IncorrectAnswerTwo",
                "another_IncorrectAnswerThree",
                "another_IncorrectAnswerFour"
        )));
        i = 0; //Элемент листа, при значения !=0 тест getById должен падать
        id = taskList.get(i).getTaskId();
        referenceId = 2;
        chapterId = 1;
    }

    @Test
    void getTaskDtoByIdDto() {
        Mockito.when(proxyService.getById(id)).thenReturn(Optional.ofNullable(taskList.get(i)));
        ChemSingleSelectTaskDto task = serviceLayer.getTaskDtoByIdDto(id);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("description");
        assertThat(task.getTaskDtoId()).isEqualTo(id);
        assertThat(task.getTaskType()).isEqualTo("SingleSelect");
    }

    @Test
    void getTaskDtoByFakeIdDto() {
        Mockito.when(proxyService.getById(id)).thenReturn(Optional.ofNullable(taskList.get(0)));
        ChemSingleSelectTaskDto task = serviceLayer.getTaskDtoByIdDto(id+"1");
        assertThat(task).isNull();
    }

    @Test
    void getAllTasksDto() {
        Mockito.when(proxyService.getAll()).thenReturn(taskList);
        List<ChemSingleSelectTaskDto> taskExamples = serviceLayer.getAllTasksDto();
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
        Mockito.when(proxyService.getAllByReferenceId(referenceId)).thenReturn(taskList.subList(1, 3));
        List<ChemSingleSelectTaskDto> taskExamples = serviceLayer.getAllTasksByReferenceIdDto(referenceId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemSingleSelectTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getTaskType()).isEqualTo("SingleSelect");
        }
    }

    @Test
    void getAllTasksByChapterIdDto() {
        Mockito.when(proxyService.getAllByChapterId(chapterId)).thenReturn(taskList.subList(0, 2));
        List<ChemSingleSelectTaskDto> taskExamples = serviceLayer.getAllTasksByChapterIdDto(chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemSingleSelectTaskDto task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("SingleSelect");
        }
    }

    @Test
    void getAllTasksByReferenceIdAndChapterIdDto() {
        Mockito.when(proxyService.getAllByReferenceIdAndChapterId(referenceId, chapterId)).thenReturn(taskList.subList(1, 2));
        List<ChemSingleSelectTaskDto> taskExamples = serviceLayer.getAllTasksByReferenceIdAndChapterIdDto(referenceId, chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(1);
        for (ChemSingleSelectTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("SingleSelect");
        }
    }
}