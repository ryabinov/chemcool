package com.chemcool.school.lesson.web.api.controllers;

import com.chemcool.school.lesson.app.LessonApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.w3c.dom.stylesheets.LinkStyle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.isA;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LessonApplication.class)
//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LessonApplication.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/testEquationsDbCreate.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//@Sql(value = {"/testDbDrop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

class LessonRestControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    @DisplayName("Получение задач по разделу")
    void findLessonByReferenceId() throws Exception {
        Integer referenceId = 3;

        List<Object> arr = new ArrayList<>()//{{add(3); add(3); add(3);}} //Если массив заполнять так то тест падает. Почему?
        ;
        arr.add(3);
        arr.add(3);
        arr.add(3);
        this.mockMvc.perform(
                get("/v1.0/getLessonByReferenceId").param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*").isNotEmpty())
                .andExpect(jsonPath("$.[0].[*].theoryReferences").value(arr))
                .andExpect(jsonPath("$.[1].[*].referenceId").value(arr))
                .andDo(print());
    }

    @Test
    @DisplayName("Получение задач по главе")
    void findLessonByChapterId() throws Exception {
        Integer chapterId = 3;
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(3);
        arr.add(3);
        arr.add(3);
        this.mockMvc.perform(
                get("/v1.0/getLessonByChapterId").param("chapterId", String.valueOf(chapterId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*").isNotEmpty())
                .andExpect(jsonPath("$.[0].[*].theoryChapter").value(arr))
                .andExpect(jsonPath("$.[1].[*].chapterId").value(arr))
                .andDo(print());
    }

    @Test
    @DisplayName("Получение задач по разделу и главе")
    void findLessonReferenceIdAndByChapterId() throws Exception {
        Integer chapterId = 3;
        Integer referenceId = 3;
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(3);
        arr.add(3);
        this.mockMvc.perform(
                get("/v1.0/getLessonByReferenceIdAndChapterId").param("referenceId", String.valueOf(referenceId))
                        .param("chapterId", String.valueOf(chapterId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*").isNotEmpty())
                .andExpect(jsonPath("$.[0].[*].theoryReferences").value(arr))
                .andExpect(jsonPath("$.[1].[*].referenceId").value(arr))
                .andExpect(jsonPath("$.[0].[*].theoryChapter").value(arr))
                .andExpect(jsonPath("$.[1].[*].chapterId").value(arr))
                .andDo(print());
    }
}