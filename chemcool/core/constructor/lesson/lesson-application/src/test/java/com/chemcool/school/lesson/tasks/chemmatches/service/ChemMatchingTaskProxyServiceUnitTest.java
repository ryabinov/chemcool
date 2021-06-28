package com.chemcool.school.lesson.tasks.chemmatches.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.matches.ChemMatchingTask;
import com.chemcool.school.lesson.domain.matches.ChemMatchingTaskExample;
import com.chemcool.school.lesson.domain.matches.CoupleForMatching;
import com.chemcool.school.lesson.service.matches.ChemMatchingTaskProxyService;
import com.chemcool.school.lesson.service.matches.ChemMatchingTaskService;
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
class ChemMatchingTaskProxyServiceUnitTest {

    @Autowired
    private ChemMatchingTaskProxyService proxyService;

    @MockBean
    private ChemMatchingTaskService service;

    private List<ChemMatchingTask> taskList = new ArrayList<>();

    private String id;
    private Integer i, referenceId, chapterId;

    @BeforeEach
    void setUp() {
        List<CoupleForMatching> coupleForMatching = new ArrayList<>();
        coupleForMatching.add(new CoupleForMatching(1L,"He", "Гелий"));
        coupleForMatching.add(new CoupleForMatching(2L,"H", "Водород"));
        coupleForMatching.add(new CoupleForMatching(3L,"O", "Кислород"));
        coupleForMatching.add(new CoupleForMatching(4L,"C", "Углерод"));

       taskList.add(ChemMatchingTask.createChemistryMatchingTask(new ChemMatchingTaskExample(
               "1",
               "description_task1",
               1,
               1,
               coupleForMatching
       )));
        taskList.add(ChemMatchingTask.createChemistryMatchingTask(new ChemMatchingTaskExample(
                "2",
                "description_task2",
                1,
                2,
                coupleForMatching
        )));
        taskList.add(ChemMatchingTask.createChemistryMatchingTask(new ChemMatchingTaskExample(
                "3",
                "description_task3",
                2,
                2,
                coupleForMatching
        )));

        i = 0; //Элемент листа, при значения !=0 тест getById должен падать
        id = taskList.get(0).getTaskId();
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
        ChemMatchingTask task = proxyService.getById(id).orElse(null);
        System.out.println("*****************\n"+task + "\n*****************\n");
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("description_task1");
        assertThat(task.getTaskId()).isEqualTo(id);
    }

    @Test
    void getByFakeId() {
        Mockito.when(service.getById(id)).thenReturn(Optional.ofNullable(taskList.get(0)));
        ChemMatchingTask task = proxyService.getById(id+"1").orElse(null);
        System.out.println("*****************\n"+task + "\n*****************\n");
        assertThat(task).isNull();
    }

    @Test
    void getAll() {
        Mockito.when(service.getAll()).thenReturn(taskList);
        List<ChemMatchingTask> taskExamples = proxyService.getAll();
        System.out.println("*****************\n"+taskExamples + "\n*****************\n");
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemMatchingTask task : taskExamples) {
            assertThat(task.getTaskId()).isNotNull();
            assertThat(task.getChapterId()).isNotNull();
            assertThat(task.getReferenceId()).isNotNull();
        }
    }

    @Test
    void getAllByChapterId() {
        Mockito.when(service.getAllByChapterId(chapterId)).thenReturn(taskList.subList(0, 2));
        List<ChemMatchingTask> taskExamples = proxyService.getAllByChapterId(chapterId);
        System.out.println("*****************\n"+taskExamples + "\n*****************\n");
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemMatchingTask task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }

    @Test
    void getAllByReferenceId() {
        Mockito.when(service.getAllByReferenceId(referenceId)).thenReturn(taskList.subList(1, 3));
        List<ChemMatchingTask> taskExamples = proxyService.getAllByReferenceId(referenceId);
        System.out.println("*****************\n"+taskExamples + "\n*****************\n");
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemMatchingTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
        }
    }

    @Test
    void getAllByReferenceIdAndChapterId() {
        Mockito.when(service.getAllByReferenceIdAndChapterId(referenceId,chapterId)).thenReturn(taskList.subList(1, 2));
        List<ChemMatchingTask> taskExamples = proxyService.getAllByReferenceIdAndChapterId(referenceId,chapterId);
        System.out.println("*****************\n"+taskExamples + "\n*****************\n");
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(1);
        for (ChemMatchingTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }
}