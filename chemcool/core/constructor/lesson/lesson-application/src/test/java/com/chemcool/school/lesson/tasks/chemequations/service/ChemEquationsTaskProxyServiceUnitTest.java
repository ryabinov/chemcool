package com.chemcool.school.lesson.tasks.chemequations.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.equation.ChemEquationsTask;
import com.chemcool.school.lesson.domain.equation.ChemEquationsTaskExample;
import com.chemcool.school.lesson.service.equation.ChemEquationsTaskProxyService;
import com.chemcool.school.lesson.service.equation.ChemEquationsTaskService;
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
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class ChemEquationsTaskProxyServiceUnitTest {

    @Autowired
    private ChemEquationsTaskProxyService proxyService;

    @MockBean
    private ChemEquationsTaskService service;

    private List<ChemEquationsTask> taskList = new ArrayList<>();

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
        ChemEquationsTask task = proxyService.getById(id).orElse(null);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("description");
        assertThat(task.getTaskId()).isEqualTo(id);
    }

    @Test
    void getByFakeId() {
        Mockito.when(service.getById(id)).thenReturn(java.util.Optional.ofNullable(taskList.get(0)));
        ChemEquationsTask task = proxyService.getById(id + "1").orElse(null);
        assertThat(task).isNull();
    }

    @Test
    void getAll() {
        Mockito.when(service.getAll()).thenReturn(taskList);
        List<ChemEquationsTask> taskExamples = proxyService.getAll();
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemEquationsTask task : taskExamples) {
            assertThat(task.getTaskId()).isNotNull();
            assertThat(task.getChapterId()).isNotNull();
            assertThat(task.getReferenceId()).isNotNull();
        }
    }

    @Test
    void getAllByChapterId() {
        Mockito.when(service.getAllByChapterId(chapterId)).thenReturn(taskList.subList(0, 2));
        List<ChemEquationsTask> taskExamples = proxyService.getAllByChapterId(chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemEquationsTask task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }

    @Test
    void getAllByReferenceId() {
        Mockito.when(service.getAllByReferenceId(referenceId)).thenReturn(taskList.subList(1, 3));
        List<ChemEquationsTask> taskExamples = proxyService.getAllByReferenceId(referenceId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemEquationsTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
        }
    }


    @Test
    void getAllByChapterIdAndReferenceId() {
        Mockito.when(service.getAllByReferenceIdAndChapterId(referenceId, chapterId)).thenReturn(taskList.subList(1, 2));
        List<ChemEquationsTask> taskExamples = proxyService.getAllByReferenceIdAndChapterId(referenceId, chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(1);
        for (ChemEquationsTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }

    @Test
    void checkAnswer() {
        boolean[] result = new boolean[]{true, true, true, true};
        Mockito.when(service.checkAnswer(id, "rightAnswer")).thenReturn(result);
        result = proxyService.checkAnswer(id, "rightAnswer");
        assertThat(result).isNotNull();
        assertThat(result).hasSize(4);
    }
}