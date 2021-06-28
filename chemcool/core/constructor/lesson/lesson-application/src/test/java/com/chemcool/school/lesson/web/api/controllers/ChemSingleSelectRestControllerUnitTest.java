package com.chemcool.school.lesson.web.api.controllers;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.singleselect.ChemSingleSelectTaskExample;
import com.chemcool.school.lesson.web.api.dto.ChemSingleSelectTaskDto;
import com.chemcool.school.lesson.web.api.service.ChemSingleSelectTaskPresentation;
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
class ChemSingleSelectRestControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ChemSingleSelectRestController controller;

    private List<ChemSingleSelectTaskDto> chemSingleSelectTask;

    @MockBean
    private ChemSingleSelectTaskPresentation presentation;

    private ChemSingleSelectTaskExample chemSingleSelectTaskExampleForTest;
    @BeforeEach
    void setUp() {
       // chemSingleSelectTaskExampleForTest = new ChemSingleSelectTaskExample("taskExampleDescription", "taskExampleCorrectAnswer",
       //         1, 1, "taskExampleIncorrectAnswerOne","taskExampleIncorrectAnswerTwo",
        //        "taskExampleIncorrectAnswerThree", "taskExampleIncorrectAnswerFour");
        chemSingleSelectTask = Collections.singletonList(
                new ChemSingleSelectTaskDto("taskDtoId","description", "correctAnswer",
                        1, 2, "incorrectAnswerOne","incorrectAnswerTwo",
                        "incorrectAnswerThree", "incorrectAnswerFour","taskType"));
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void contextTest() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    @DisplayName("Получение задач по главе")
    void findTaskByChapter() throws Exception {
        Integer chapterId = 1;
        Mockito.when(presentation.getAllTasksByChapterIdDto(chapterId)).thenReturn(chemSingleSelectTask);
        this.mockMvc.perform(
                get("/v1.0/findSingleSelectTaskByChapterId").param("chapterId", String.valueOf(chapterId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].chapterId").value(chapterId))
                .andDo(print());
    }

    @Test
    @DisplayName("Получение задач по разделу")
    void findTaskByReferences() throws Exception {
        Integer referenceId = 2;
        Mockito.when(presentation.getAllTasksByReferenceIdDto(referenceId)).thenReturn(chemSingleSelectTask);
        this.mockMvc.perform(
                get("/v1.0/findSingleSelectTaskByReferenceId").param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].referenceId").value(referenceId))
                .andDo(print());
    }

    @Test
    @DisplayName("Получение задач по главе и разделу")
    void findEquationsTaskByChapterAndReferences() throws Exception {
        Integer chapterId = 1;
        Integer referenceId = 2;
        Mockito.when(presentation.getAllTasksByReferenceIdAndChapterIdDto(referenceId, chapterId)).thenReturn(chemSingleSelectTask);
        this.mockMvc.perform(
                get("/v1.0/findSingleSelectTaskByReferenceIdAndChapterId").param("chapterId", String.valueOf(chapterId))
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
        Mockito.when(presentation.getAllTasksByReferenceIdAndChapterIdDto(referenceId, chapterId)).thenReturn(Collections.emptyList());
        this.mockMvc.perform(
                get("/v1.0/findSingleSelectTaskByReferenceIdAndChapterId").param("chapterId", String.valueOf(chapterId))
                        .param("referenceId", String.valueOf(referenceId))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.*").value(IsEmptyCollection.empty()))
                .andDo(print());
    }
}