package com.chemcool.school.lesson.tasks.chemsingleselect.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.singleselect.ChemSingleSelectTask;
import com.chemcool.school.lesson.domain.singleselect.ChemSingleSelectTaskExample;
import com.chemcool.school.lesson.service.singleselect.ChemSingleSelectTaskProxyService;
import com.chemcool.school.lesson.service.singleselect.ChemSingleSelectTaskService;
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

/*
Тест для ChemSingleSelectTaskProxyService. Вместо ChemSingleSelectTaskService заглушка
Создаем лист из трех тасок. Их подставляем в заглушку.
 */

@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class ChemSingleSelectTaskProxyServiceUnitTest {

    @Autowired
    private ChemSingleSelectTaskProxyService proxyService;

    @MockBean
    private ChemSingleSelectTaskService service;

    private List<ChemSingleSelectTask> taskList = new ArrayList<>();

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
    public void contextTest() throws Exception {
        assertThat(proxyService).isNotNull();
    }

    @Test
    void getById() {
        Mockito.when(service.getById(id)).thenReturn(Optional.ofNullable(taskList.get(i)));
        ChemSingleSelectTask task = proxyService.getById(id).orElse(null);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("description");
        assertThat(task.getTaskId()).isEqualTo(id);
    }

    @Test
    void getByFakeId() {
        Mockito.when(service.getById(id)).thenReturn(Optional.ofNullable(taskList.get(i)));
        ChemSingleSelectTask task = proxyService.getById(id + "1").orElse(null);
        assertThat(task).isNull();
    }

    @Test
    void getAll() {
        Mockito.when(service.getAll()).thenReturn(taskList);
        List<ChemSingleSelectTask> taskExamples = proxyService.getAll();
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).isEqualTo(taskList);
        assertThat(taskExamples).hasSize(3);
        for (ChemSingleSelectTask task : taskExamples) {
            assertThat(task.getTaskId()).isNotNull();
            assertThat(task.getChapterId()).isNotNull();
            assertThat(task.getReferenceId()).isNotNull();
        }
    }

    @Test
    void getAllByChapterId() {
        Mockito.when(service.getAllByChapterId(chapterId)).thenReturn(taskList.subList(0, 2));
        List<ChemSingleSelectTask> taskExamples = proxyService.getAllByChapterId(chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemSingleSelectTask task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }

    @Test
    void getAllByReferenceId() {
        Mockito.when(service.getAllByReferenceId(referenceId)).thenReturn(taskList.subList(1, 3));
        List<ChemSingleSelectTask> taskExamples = proxyService.getAllByReferenceId(referenceId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemSingleSelectTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
        }
    }

    @Test
    void getAllByReferenceIdAndChapterId() {
        Mockito.when(service.getAllByReferenceIdAndChapterId(referenceId,chapterId)).thenReturn(taskList.subList(1, 2));
        List<ChemSingleSelectTask> taskExamples = proxyService.getAllByReferenceIdAndChapterId(referenceId,chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(1);
        for (ChemSingleSelectTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }
}