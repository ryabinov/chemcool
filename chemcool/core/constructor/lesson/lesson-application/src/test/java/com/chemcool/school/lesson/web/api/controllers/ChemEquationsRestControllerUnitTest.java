package com.chemcool.school.lesson.web.api.controllers;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.web.api.dto.ChemEquationsTaskDto;
import com.chemcool.school.lesson.web.api.service.ChemEquationsTaskPresentation;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LessonApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@TestPropertySource("/application-test.properties")
class ChemEquationsRestControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChemEquationsRestController controller;

    private List<ChemEquationsTaskDto> chemEquationsTask;

    @MockBean
    private ChemEquationsTaskPresentation presentation;

    @BeforeEach
    void setUp() {
        chemEquationsTask = Collections.singletonList(
                new ChemEquationsTaskDto("id", "description", 1, 2, "Equations"));
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    @DisplayName("Тест контекста")
    public void contextTest() {
        assertThat(controller).isNotNull();
    }

    @Test
    @DisplayName("Получение задач по разделу")
    void findEquationsTaskByReference() throws Exception {
        Integer referenceId = 2;
        Mockito.when(presentation.getAllChemistryEquationsByChapterIdDto(referenceId)).thenReturn(chemEquationsTask);
        this.mockMvc.perform(
                get("/v1.0/findEquationsTaskByChapterId").param("chapterId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].referenceId").value(referenceId))
                .andDo(print());
    }

    @Test
    @DisplayName("Получение задач по главе")
    void findEquationsTaskByChapter() throws Exception {
        Integer chapterId = 1;
        Mockito.when(presentation.getAllChemistryEquationsByChapterIdDto(chapterId)).thenReturn(chemEquationsTask);
        this.mockMvc.perform(
                get("/v1.0/findEquationsTaskByChapterId").param("chapterId", String.valueOf(chapterId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].chapterId").value(chapterId))
                .andDo(print());
    }

    @Test
    @DisplayName("Получение задач по главе и разделу")
    void findEquationsTaskByChapterAndReferences() throws Exception {
        Integer chapterId = 1;
        Integer referenceId = 2;
        Mockito.when(presentation.getAllChemistryEquationsByReferenceIdAndChapterIdDto(referenceId, chapterId)).thenReturn(chemEquationsTask);
        this.mockMvc.perform(
                get("/v1.0/findEquationsTaskByReferenceIdAndChapterId").param("chapterId", String.valueOf(chapterId))
                        .param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].chapterId").value(chapterId))
                .andExpect(jsonPath("$[0].referenceId").value(referenceId))
                .andDo(print());
    }

    @Test
    @DisplayName("Проверка на несуществующие главу или раздел")
    void findEquationsTaskByFakeChapterAndReferences() throws Exception {
        int chapterId = 5;
        int referenceId = 5;
        Mockito.when(presentation.getAllChemistryEquationsByReferenceIdAndChapterIdDto(referenceId, chapterId)).thenReturn(Collections.emptyList());
        this.mockMvc.perform(
                get("/v1.0/findEquationsTaskByReferenceIdAndChapterId").param("chapterId", String.valueOf(chapterId))
                        .param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*").value(IsEmptyCollection.empty()))
                .andDo(print());
    }
}



























/*
 private String description;
    private String rightAnswer;
    private int chapterId;
    private int referenceId;
    public void addingTestTheoryDataBase(TheoryDto theoryDto) {
        presentation.createChemistryTheoryDto(theoryDto);
    }
 System.out.println(chemEquationsTaskExampleForTest);
        int chapterId = chemEquationsTaskExampleForTest.getChapterId();
        //addingChemEquationsTaskDataBase(chemEquationsTaskExampleForTest);
        //List<ChemEquationsTask> task = service.getAllByChapterId(chapterId);
       // assertThat(task).isNotNull();
        //System.out.println(task);

        System.out.println(chapterId);
       // mockMvc.perform(get(BASE_URL + "/findEquationsTaskByChapter?chapter={chapterId}", chapterId))
        //         .andExpect(status().isOk())
            //    .andReturn();

                //.andExpect(jsonPath("$.chapterId").value(chapterId))
        //.andExpect(jsonPath("$.description").value("description"))
        ;

          System.out.println(chemEquationsTaskExampleForTest);

        Mockito.when(service.add(Mockito.any())).thenReturn(chemEquationsTaskExampleForTest)


        System.out.println("Complete");
        int chapterId = chemEquationsTaskExampleForTest.getChapterId();
        System.out.println("*****************************888");
        System.out.println(objectMapper.writeValueAsString(chemEquationsTaskExampleForTest));
        mockMvc.perform(
                get(BASE_URL + "/findEquationsTaskByChapter?chapter={chapterId}", chapterId).
                        content(objectMapper.writeValueAsString(chemEquationsTaskExampleForTest))
                        .contentType(MediaType.APPLICATION_JSON)
                        )
        .andExpect(status().isNotFound())

                //.andExpect(status().isOk())
                ;
 */
