package com.chemcool.school.lesson.web.api.controllers;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.web.api.dto.ChemFixedAnswerTaskDto;
import com.chemcool.school.lesson.web.api.service.ChemFixedAnswerTaskPresentation;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = LessonApplication.class)
@AutoConfigureMockMvc
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@TestPropertySource("/application-test.properties")
class ChemFixedAnswerRestControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChemFixedAnswerRestController controller;

    private List<ChemFixedAnswerTaskDto> chemFixedAnswerTasks;

    @MockBean
    private ChemFixedAnswerTaskPresentation presentation;

    @BeforeEach
    void setUp() {
        chemFixedAnswerTasks = Collections.singletonList(
                new ChemFixedAnswerTaskDto("taskId","description", "rightAnswer", 1, 2, "FixedAnswer"));
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void contextTest() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void findFixedAnswerTaskByChapter() throws Exception {
        Integer chapterId = 1;
        Mockito.when(presentation.getAllChemistryFixedAnswerByChapterIdDto(chapterId)).thenReturn(chemFixedAnswerTasks);
        this.mockMvc.perform(
                get("/v1.0/findFixedAnswerTaskByChapterId").param("chapterId", String.valueOf(chapterId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].chapterId").value(chapterId))
                .andDo(print());
    }

    @Test
    void findFixedAnswerTaskByReferences() throws Exception {
        Integer referenceId = 2;
        Mockito.when(presentation.getAllChemistryFixedAnswerByReferenceIdDto(referenceId)).thenReturn(chemFixedAnswerTasks);
        this.mockMvc.perform(
                get("/v1.0/findFixedAnswerTaskByReferenceId").param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].referenceId").value(referenceId))
                .andDo(print());
    }

    @Test
    @DisplayName("Получение задач по главе и разделу")
    void findEquationsTaskByChapterAndReferences() throws Exception {
        Integer chapterId = 1;
        Integer referenceId = 2;
        Mockito.when(presentation.getAllChemistryFixedAnswerByReferenceIdAndChapterIdDto(referenceId, chapterId)).thenReturn(chemFixedAnswerTasks);
        this.mockMvc.perform(
                get("/v1.0/findFixedAnswerTaskByReferenceAndChapter").param("chapterId", String.valueOf(chapterId))
                        .param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].chapterId").value(chapterId))
                .andExpect(jsonPath("$[0].referenceId").value(referenceId))
                .andDo(print());
    }

    @Test
    @DisplayName("Проверка на несуществующие главу или раздел")
    void findEquationsTaskByFakeChapterAndReferences() throws Exception {
        Integer chapterId = 5;
        Integer referenceId = 5;
        Mockito.when(presentation.getAllChemistryFixedAnswerByReferenceIdAndChapterIdDto(referenceId, chapterId)).thenReturn(Collections.emptyList());
        this.mockMvc.perform(
                get("/v1.0/findFixedAnswerTaskByReferenceAndChapter").param("chapterId", String.valueOf(chapterId))
                        .param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*").value(IsEmptyCollection.empty()))
                .andDo(print());
    }
}