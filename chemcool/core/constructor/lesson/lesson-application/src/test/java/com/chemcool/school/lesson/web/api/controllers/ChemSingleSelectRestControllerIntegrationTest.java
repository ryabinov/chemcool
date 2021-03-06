package com.chemcool.school.lesson.web.api.controllers;

import com.chemcool.school.lesson.app.LessonApplication;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LessonApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@TestPropertySource("/application-test.properties")
@Sql(value = {"/testSingleSelectDbCreate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/testDbDrop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class ChemSingleSelectRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChemSingleSelectRestController controller;

    @Test
    public void contextTest() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    @DisplayName("?????????????????? ?????????? ???? ??????????")
    void findTaskByChapter() throws Exception {
        Integer chapterId = 2;
        this.mockMvc.perform(
                get("/v1.0/findSingleSelectTaskByChapterId").param("chapterId", String.valueOf(chapterId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(chapterId)))
                .andExpect(jsonPath("$[0].chapterId").value(chapterId))
                .andDo(print());
    }

    @Test
    @DisplayName("?????????????????? ?????????? ???? ??????????????")
    void findTaskByReferences() throws Exception {
        Integer referenceId = 2;
        this.mockMvc.perform(
                get("/v1.0/findSingleSelectTaskByReferenceId").param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(referenceId)))
                .andExpect(jsonPath("$[0].referenceId").value(referenceId))
                .andDo(print());
    }

    @Test
    @DisplayName("?????????????????? ?????????? ???? ?????????? ?? ??????????????")
    void findEquationsTaskByChapterIdAndReferenceId() throws Exception {
        Integer chapterId = 3;
        Integer referenceId = 3;
        this.mockMvc.perform(
                get("/v1.0/findSingleSelectTaskByReferenceIdAndChapterId")
                        .param("chapterId", String.valueOf(chapterId))
                        .param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$[0].chapterId").value(chapterId))
                .andExpect(jsonPath("$[0].referenceId").value(referenceId))
                .andDo(print());
    }

    @Test
    @DisplayName("???????????????? ???? ???????????????????????????? ?????????? ?????? ????????????")
    void findEquationsTaskByFakeChapterAndReferences() throws Exception {
        Integer chapterId = 5;
        Integer referenceId = 5;
        this.mockMvc.perform(
                get("/v1.0/findSingleSelectTaskByReferenceIdAndChapterId").param("chapterId", String.valueOf(chapterId))
                        .param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*").value(IsEmptyCollection.empty()))
                .andDo(print());
    }
}