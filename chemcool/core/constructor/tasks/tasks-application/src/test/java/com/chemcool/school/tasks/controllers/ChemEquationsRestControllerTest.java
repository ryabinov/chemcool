package com.chemcool.school.tasks.controllers;

import com.chemcool.school.tasks.app.TasksApplication;
import com.chemcool.school.tasks.controllers.chemequations.ChemEquationsRestController;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TasksApplication.class)
@Transactional
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/testEquationDb.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ChemEquationsRestControllerTest {

    @Autowired
    private ChemEquationsRestController chemEquationsRestController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("Проверка контекста")
    public void contextLoader() {
        assertThat(chemEquationsRestController).isNotNull();
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(get("/chemEquations/v.1.0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void getAllByChapterId() throws Exception {
        mockMvc.perform(get("/chemEquations/v.1.0/chapter/" + "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void getAllByReferenceId() throws Exception {
        mockMvc.perform(get("/chemEquations/v.1.0/reference/" + "1/" + "2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void getEquationsTaskById() throws Exception {
        mockMvc.perform(get("/chemEquations/v.1.0/" + "1"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void getRandomTask() throws Exception {
        mockMvc.perform(get("/chemEquations/v.1.0/randomTask")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void saveNewEquationsTask() throws Exception {
        mockMvc.perform(post("/chemEquations/v.1.0/")
                .param("taskId", "test")
                .param("description", "test")
                .param("chapterId", "1")
                .param("referenceId", "1")
                .param("taskType", "type")
                .param("rightAnswer", "testAnswer")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void saveEquationsTask() throws Exception {
        mockMvc.perform(put("/chemEquations/v.1.0/")
                .param("taskId", "test")
                .param("description", "test")
                .param("chapterId", "2")
                .param("referenceId", "2")
                .param("taskType", "type")
                .param("rightAnswer", "testAnswer")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void deleteEquationsTask() throws Exception {
        mockMvc.perform(delete("/chemEquations/v.1.0/" + "2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void checkAnswer() throws Exception {
        mockMvc.perform(post("/chemEquations/v.1.0/checkAnswer")
                .param("taskId","1")
                .param("userAnswer","CuSO4+2NaOH→Na2SO4+Cu(OH)2↓")
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answerResult").value(true))
                .andExpect(jsonPath("$.score").value(10));
    }
}
