package com.chemcool.school.lesson.web.api.controllers;

import com.chemcool.school.lesson.app.LessonApplication;
import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsNot;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LessonApplication.class)
//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LessonApplication.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/testEquationsDbCreate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/testDbDrop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ChemEquationsRestControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChemEquationsRestController controller;

    @Test
    @DisplayName("Тест контекста")
    public void contextTest() {
        assertThat(controller).isNotNull();
    }

    @Test
    @DisplayName("Получение задач по разделу")
    void findEquationsTaskByReferenceId() throws Exception {
        Integer referenceId = 3;
        this.mockMvc.perform(
                get("/v1.0/findEquationsTaskByReferenceId").param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(referenceId)))
                .andExpect(jsonPath("$[0].referenceId").value(referenceId))
                .andDo(print());
    }

    @Test
    @DisplayName("Получение задач по главе")
    void findEquationsTaskByChapterId() throws Exception {
        Integer chapterId = 2;
        this.mockMvc.perform(
                get("/v1.0/findEquationsTaskByChapterId").param("chapterId", String.valueOf(chapterId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(chapterId)))
                .andExpect(jsonPath("$[0].chapterId").value(chapterId))
                .andDo(print());
    }

    @Test
    @DisplayName("Получение задач по главе и разделу")
    void findEquationsTaskByChapterIdAndReferenceId() throws Exception {
        Integer chapterId = 3;
        Integer referenceId = 3;
        this.mockMvc.perform(
                get("/v1.0/findEquationsTaskByReferenceIdAndChapterId")
                        .param("chapterId", String.valueOf(chapterId))
                        .param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$[0].chapterId").value(chapterId))
                .andExpect(jsonPath("$[0].referenceId").value(referenceId))
                .andDo(print());
    }

    @Test
    @DisplayName("Проверка на несуществующие главу или раздел")
    void findEquationsTaskByFakeChapterAndReferences() throws Exception {
        Integer chapterId = 5;
        Integer referenceId = 5;
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
    public void addingTestTheoryDataBase(ChemTheoryDto theoryDto) {
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
