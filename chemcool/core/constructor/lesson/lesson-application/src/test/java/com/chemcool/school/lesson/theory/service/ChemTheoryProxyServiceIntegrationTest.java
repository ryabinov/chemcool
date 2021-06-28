package com.chemcool.school.lesson.theory.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.theory.domain.ChemTheory;
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
@Sql(value = {"/testTheoryDbCreate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/testDbDrop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ChemTheoryProxyServiceIntegrationTest {

    @Autowired
    private ChemTheoryProxyService proxyService;

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
        ChemTheory theory = proxyService.getById(id).orElse(null);
        System.out.println("*****************\n"+theory + "\n*****************\n");
        assertThat(theory).isNotNull();
        assertThat(theory.getTheoryDescription()).isEqualTo("theory_description1");
        assertThat(theory.getTheoryId()).isEqualTo(id);
    }

    @Test
    void getByFakeId() {
        ChemTheory theory = proxyService.getById(id + "1").orElse(null);
        System.out.println("*****************\n"+theory + "\n*****************\n");
        assertThat(theory).isNull();
    }

    @Test
    void getAll() {
        List<ChemTheory> theoryExamples = proxyService.getAll();
        System.out.println("*****************\n"+theoryExamples + "\n*****************\n");
        assertThat(theoryExamples).isNotNull();
        assertThat(theoryExamples).hasSize(10);
        for (ChemTheory theory : theoryExamples) {
            assertThat(theory.getTheoryId()).isNotNull();
            assertThat(theory.getTheoryChapter()).isNotNull();
            assertThat(theory.getTheoryReferences()).isNotNull();
        }
    }

    @Test
    void getAllByChapterId() {
        List<ChemTheory> theoryExamples = proxyService.getAllByChapterId(chapterId);
        System.out.println("*****************\n"+theoryExamples + "\n*****************\n");
        assertThat(theoryExamples).isNotNull();
        assertThat(theoryExamples).hasSize(3);
        for (ChemTheory theory : theoryExamples) {
            assertThat(theory.getTheoryChapter()).isEqualTo(chapterId);
        }
    }

    @Test
    void getAllByReferenceId() {
        List<ChemTheory> theoryExamples = proxyService.getAllByReferenceId(referenceId);
        System.out.println("*****************\n"+theoryExamples + "\n*****************\n");
        assertThat(theoryExamples).isNotNull();
        assertThat(theoryExamples).hasSize(3);
        for (ChemTheory theory : theoryExamples) {
            assertThat(theory.getTheoryReferences()).isEqualTo(referenceId);
        }
    }

    @Test
    void getAllByReferenceIdAndChapterId() {
        List<ChemTheory> theoryExamples = proxyService.getAllByReferenceIdAndChapterId(referenceId,chapterId);
        System.out.println("*****************\n"+theoryExamples + "\n*****************\n");
        assertThat(theoryExamples).isNotNull();
        assertThat(theoryExamples).hasSize(2);
        for (ChemTheory theory : theoryExamples) {
            assertThat(theory.getTheoryReferences()).isEqualTo(referenceId);
            assertThat(theory.getTheoryChapter()).isEqualTo(chapterId);
        }
    }
}