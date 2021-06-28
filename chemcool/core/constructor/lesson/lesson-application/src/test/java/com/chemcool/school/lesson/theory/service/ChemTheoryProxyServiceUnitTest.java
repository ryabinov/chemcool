package com.chemcool.school.lesson.theory.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.theory.domain.ChemTheory;
import com.chemcool.school.lesson.theory.domain.ChemTheoryExample;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class ChemTheoryProxyServiceUnitTest {

    @Autowired
    private ChemTheoryProxyService proxyService;

    @MockBean
    private ChemTheoryPageService service;

    private List<ChemTheory> theoryList = new ArrayList<>();

    private String id;
    private Integer i, referenceId, chapterId;

    @BeforeEach
    void setUp() {
        theoryList.add(ChemTheory.createChemistryTheory(new ChemTheoryExample(
                "theory_name",
                "theory_description",
                1,
                1
        )));
        theoryList.add(ChemTheory.createChemistryTheory(new ChemTheoryExample(
                "another_theory_name",
                "another_theory_description",
                1,
                2
        )));
        theoryList.add(ChemTheory.createChemistryTheory(new ChemTheoryExample(
                "another_theory_name",
                "another_theory_description",
                2,
                2
        )));
        System.out.println("*****************\n"+theoryList + "\n*****************\n");
        i = 0; //Элемент листа, при значения !=0 тест getById должен падать
        id = theoryList.get(i).getTheoryId();
        referenceId = 2;
        chapterId = 1;
    }

    @Test
    public void contextTest() throws Exception {
        assertThat(proxyService).isNotNull();
    }

    @Test
    void getById() {
        Mockito.when(service.getAllByTheoryId(id)).thenReturn(java.util.Optional.ofNullable(theoryList.get(i)));
        ChemTheory theory = proxyService.getById(id).orElse(null);
        System.out.println("*****************\n"+theory + "\n*****************\n");
        assertThat(theory).isNotNull();
        assertThat(theory.getTheoryDescription()).isEqualTo("theory_description");
        assertThat(theory.getTheoryId()).isEqualTo(id);
    }

    @Test
    void getByFakeId() {
        Mockito.when(service.getAllByTheoryId(id)).thenReturn(java.util.Optional.ofNullable(theoryList.get(i)));
        ChemTheory theory = proxyService.getById(id + "1").orElse(null);
        System.out.println("*****************\n"+theory + "\n*****************\n");
        assertThat(theory).isNull();
    }

    @Test
    void getAll() {
        Mockito.when(service.getAll()).thenReturn(theoryList);
        List<ChemTheory> theoryExamples = proxyService.getAll();
        System.out.println("*****************\n"+theoryExamples + "\n*****************\n");
        assertThat(theoryExamples).isNotNull();
        assertThat(theoryExamples).isEqualTo(theoryList);
        assertThat(theoryExamples).hasSize(3);
        for (ChemTheory theory : theoryExamples) {
            assertThat(theory.getTheoryId()).isNotNull();
            assertThat(theory.getTheoryChapter()).isNotNull();
            assertThat(theory.getTheoryReferences()).isNotNull();
        }
    }

    @Test
    void getAllByChapterId() {
        Mockito.when(service.getAllByChapterId(chapterId)).thenReturn(theoryList.subList(0,2));
        List<ChemTheory> theoryExamples = proxyService.getAllByChapterId(chapterId);
        System.out.println("*****************\n"+theoryExamples + "\n*****************\n");
        assertThat(theoryExamples).isNotNull();
        assertThat(theoryExamples).hasSize(2);
        for (ChemTheory theory : theoryExamples) {
            assertThat(theory.getTheoryChapter()).isEqualTo(chapterId);
        }
    }

    @Test
    void getAllByReferenceId() {
        Mockito.when(service.getAllByReferenceId(referenceId)).thenReturn(theoryList.subList(1,3));
        List<ChemTheory> theoryExamples = proxyService.getAllByReferenceId(referenceId);
        System.out.println("*****************\n"+theoryExamples + "\n*****************\n");
        assertThat(theoryExamples).isNotNull();
        assertThat(theoryExamples).hasSize(2);
        for (ChemTheory theory : theoryExamples) {
            assertThat(theory.getTheoryReferences()).isEqualTo(referenceId);
        }
    }

    @Test
    void getAllByReferenceIdAndChapterId() {
        Mockito.when(service.getAllByReferenceIdAndChapterId(referenceId,chapterId)).thenReturn(theoryList.subList(1, 2));
        List<ChemTheory> theoryExamples = proxyService.getAllByReferenceIdAndChapterId(referenceId,chapterId);
        System.out.println("*****************\n"+theoryExamples + "\n*****************\n");
        assertThat(theoryExamples).isNotNull();
        assertThat(theoryExamples).hasSize(1);
        for (ChemTheory theory : theoryExamples) {
            assertThat(theory.getTheoryReferences()).isEqualTo(referenceId);
            assertThat(theory.getTheoryChapter()).isEqualTo(chapterId);
        }
    }
}