package com.chemcool.school.lesson.tasks.chemfixedanswer.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.fixedanswer.ChemFixedAnswerTask;
import com.chemcool.school.lesson.service.fixedanswer.ChemFixedAnswerTaskProxyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@Transactional
@TestPropertySource("/application-test.properties")
@Sql(value = {"/testFixedAnswerDbCreate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/testDbDrop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ChemFixedAnswerTaskProxyServiceIntegrationTest {

    @Autowired
    private ChemFixedAnswerTaskProxyService proxyService;

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
        ChemFixedAnswerTask task = proxyService.getById(id).orElse(null);
        System.out.println("*****************\n" + task + "\n*****************\n");
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("task1");
        assertThat(task.getTaskId()).isEqualTo(id);
    }

    @Test
    void getByFakeId() {
        ChemFixedAnswerTask task = proxyService.getById(id + "1").orElse(null);
        System.out.println("*****************\n" + task + "\n*****************\n");
        assertThat(task).isNull();
    }

    @Test
    void getAll() {
        List<ChemFixedAnswerTask> taskExamples = proxyService.getAll();
        System.out.println("*****************\n" + taskExamples + "\n*****************\n");
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(10);
        for (ChemFixedAnswerTask task : taskExamples) {
            assertThat(task.getTaskId()).isNotNull();
            assertThat(task.getChapterId()).isNotNull();
            assertThat(task.getReferenceId()).isNotNull();
        }
    }

    @Test
    void getAllByChapterId() {
        List<ChemFixedAnswerTask> taskExamples = proxyService.getAllByChapterId(chapterId);
        System.out.println("*****************\n" + taskExamples + "\n*****************\n");
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemFixedAnswerTask task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }

    @Test
    void getAllByReferenceId() {
        List<ChemFixedAnswerTask> taskExamples = proxyService.getAllByReferenceId(referenceId);
        System.out.println("*****************\n"+taskExamples + "\n*****************\n");
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemFixedAnswerTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
        }
    }

    @Test
    void getAllByReferenceIdAndChapterId() {
        List<ChemFixedAnswerTask> taskExamples = proxyService.getAllByReferenceIdAndChapterId(referenceId,chapterId);
        System.out.println("*****************\n"+taskExamples + "\n*****************\n");
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemFixedAnswerTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }
}