package com.chemcool.school.lesson.tasks.chemmatches.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.matches.ChemMatchingTask;
import com.chemcool.school.lesson.service.matches.ChemMatchingTaskProxyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/testMatchesDbCreate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/testDbDrop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ChemMatchingTaskProxyServiceIntegrationTest {

    @Autowired
    private ChemMatchingTaskProxyService proxyService;

    private String id;
    private Integer referenceId, chapterId;

    @BeforeEach
    void setUp() {
        id = "1";
        referenceId = 3;
        chapterId = 3;
    }

    @Test
    public void contextTest() throws Exception {
        assertThat(proxyService).isNotNull();
    }

    @Test
    void getById() {
        ChemMatchingTask task = proxyService.getById(id).orElse(null);
        System.out.println("*****************\n"+task + "\n*****************\n");
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("task1");
        assertThat(task.getTaskId()).isEqualTo(id);
    }

    @Test
    void getByFakeId() {
        ChemMatchingTask task = proxyService.getById(id+"1").orElse(null);
        System.out.println("*****************\n"+task + "\n*****************\n");
        assertThat(task).isNull();
    }

    @Test
    void getAll() {
        List<ChemMatchingTask> taskExamples = proxyService.getAll();
        System.out.println("*****************\n"+taskExamples + "\n*****************\n");
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(10);
        for (ChemMatchingTask task : taskExamples) {
            assertThat(task.getTaskId()).isNotNull();
            assertThat(task.getChapterId()).isNotNull();
            assertThat(task.getReferenceId()).isNotNull();
        }
    }

    @Test
    void getAllByChapterId() {
        List<ChemMatchingTask> taskExamples = proxyService.getAllByChapterId(chapterId);
        System.out.println("*****************\n"+taskExamples + "\n*****************\n");
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemMatchingTask task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }

    @Test
    void getAllByReferenceId() {
        List<ChemMatchingTask> taskExamples = proxyService.getAllByReferenceId(referenceId);
        System.out.println("*****************\n"+taskExamples + "\n*****************\n");
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemMatchingTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
        }
    }

    @Test
    void getAllByReferenceIdAndChapterId() {
        List<ChemMatchingTask> taskExamples = proxyService.getAllByReferenceIdAndChapterId(referenceId,chapterId);
        System.out.println("*****************\n"+taskExamples + "\n*****************\n");
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemMatchingTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }
}