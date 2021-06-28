package com.chemcool.school.lesson.tasks.chemsingleselect.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.singleselect.ChemSingleSelectTask;
import com.chemcool.school.lesson.service.singleselect.ChemSingleSelectTaskProxyService;
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

/*

 */

@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@Transactional
@TestPropertySource("/application-test.properties")
@Sql(value = {"/testSingleSelectDbCreate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/testDbDrop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ChemSingleSelectTaskProxyServiceIntegrationTest {

    @Autowired
    private ChemSingleSelectTaskProxyService proxyService;

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
        ChemSingleSelectTask task = proxyService.getById(id).orElse(null);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("description");
        assertThat(task.getTaskId()).isEqualTo(id);
    }

    @Test
    void getByFakeId() {
        ChemSingleSelectTask task = proxyService.getById(id + "1").orElse(null);
        assertThat(task).isNull();
    }

    @Test
    void getAll() {
        List<ChemSingleSelectTask> taskExamples = proxyService.getAll();
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(10);
        for (ChemSingleSelectTask task : taskExamples) {
            assertThat(task.getTaskId()).isNotNull();
            assertThat(task.getChapterId()).isNotNull();
            assertThat(task.getReferenceId()).isNotNull();
        }
    }

    @Test
    void getAllByChapterId() {
        List<ChemSingleSelectTask> taskExamples = proxyService.getAllByChapterId(chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemSingleSelectTask task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }

    @Test
    void getAllByReferenceId() {
        List<ChemSingleSelectTask> taskExamples = proxyService.getAllByReferenceId(referenceId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemSingleSelectTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
        }
    }

    @Test
    void getAllByReferenceIdAndChapterId() {
        List<ChemSingleSelectTask> taskExamples = proxyService.getAllByReferenceIdAndChapterId(referenceId,chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemSingleSelectTask task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
        }
    }
}