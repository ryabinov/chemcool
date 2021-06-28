package com.chemcool.school.tasks.controllers;

import com.chemcool.school.tasks.app.TasksApplication;
import com.chemcool.school.tasks.controllers.chemmatches.ChemistryMatchingTaskRestController;
import com.chemcool.school.tasks.dto.chemmatches.ChemistryMatchingTaskDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = TasksApplication.class)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/testMatchingDb.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class ChemistryMatchingTaskRestControllerTest {

    @Autowired
    private ChemistryMatchingTaskRestController chemistryMatchingTaskRestController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Проверка контекста")
    public void contextLoader() {
        assertThat(chemistryMatchingTaskRestController).isNotNull();
    }

    @Test
    void getAllTasks() throws Exception {
        mockMvc.perform(get("/chemistryMatching/v1.0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void getTaskById() throws Exception {
        mockMvc.perform(get("/chemistryMatching/v1.0/" + "2"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void createNewTask() throws Exception {
        mockMvc.perform(post("/chemistryMatching/v1.0/")
                .content(objectMapper.writeValueAsString(new ChemistryMatchingTaskDto("1", "1", 1, 4, null)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void updateTask() throws Exception {
        mockMvc.perform(put("/chemistryMatching/v1.0")
                .content(objectMapper.writeValueAsString(new ChemistryMatchingTaskDto("2", "3", 2, 1, null)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    void deleteTask() throws Exception {
        mockMvc.perform(delete("/chemistryMatching/v1.0/" + "2"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

}