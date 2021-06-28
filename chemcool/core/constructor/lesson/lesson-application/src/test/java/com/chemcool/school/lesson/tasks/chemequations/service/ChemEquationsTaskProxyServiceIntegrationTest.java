package com.chemcool.school.lesson.tasks.chemequations.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.equation.ChemEquationsTask;
import com.chemcool.school.lesson.service.equation.ChemEquationsTaskProxyService;
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
@Sql(value = {"/testEquationsDbCreate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/testDbDrop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ChemEquationsTaskProxyServiceIntegrationTest {

    @Autowired
    private ChemEquationsTaskProxyService proxyService;

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
        ChemEquationsTask task = proxyService.getById(id).orElse(null);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("task1");
        assertThat(task.getTaskId()).isEqualTo(id);
    }

    @Test
    void getByFakeId() {
        ChemEquationsTask task = proxyService.getById(id + "1").orElse(null);
        assertThat(task).isNull();
    }

    @Test
    void getAll() {
        List<ChemEquationsTask> taskExamples = proxyService.getAll();
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(10);
        for (ChemEquationsTask task : taskExamples) {
            assertThat(task.getTaskId()).isNotNull();
            assertThat(task.getChapterId()).isNotNull();
            assertThat(task.getReferenceId()).isNotNull();
        }
    }

    @Test
    void getAllByChapterId() {
        List<ChemEquationsTask> taskExamples = proxyService.getAllByChapterId(chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemEquationsTask task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }

    @Test
    void getAllByReferenceId() {
        List<ChemEquationsTask> taskExamples = proxyService.getAllByReferenceId(referenceId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemEquationsTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
        }
    }


    @Test
    void getAllByChapterIdAndReferenceId() {
        List<ChemEquationsTask> taskExamples = proxyService.getAllByReferenceIdAndChapterId(3, 3);
        System.out.println(taskExamples);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemEquationsTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }

    @Test
    void checkAnswer() {
        boolean[] result = proxyService.checkAnswer(id, "CuSO4+2NaOH→Cu(OH)2↓+Na2SO4");
        assertThat(result).isNotNull();
        assertThat(result).hasSize(4);
        assertThat(result).containsOnly(true);
    }


}