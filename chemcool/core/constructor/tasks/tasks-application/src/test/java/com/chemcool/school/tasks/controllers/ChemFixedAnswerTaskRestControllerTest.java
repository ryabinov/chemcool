package com.chemcool.school.tasks.controllers;

import com.chemcool.school.tasks.app.TasksApplication;
import com.chemcool.school.tasks.controllers.chemfixedanswer.ChemFixedAnswerTaskRestController;
import com.chemcool.school.tasks.dto.chemfixedanswer.ChemFixedAnswerTaskDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
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

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@TestPropertySource("/application-test.properties")
@SpringBootTest
@ContextConfiguration(classes = TasksApplication.class)
@AutoConfigureMockMvc
@Sql(value = {"/testFixedAnswerDb.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ChemFixedAnswerTaskRestControllerTest {

    @Autowired
    private ChemFixedAnswerTaskRestController chemFixedAnswerTaskRestController;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;


    ChemFixedAnswerTaskDto chemFixedAnswerTaskDto1;
    ChemFixedAnswerTaskDto chemFixedAnswerTaskDto2;
    ChemFixedAnswerTaskDto chemFixedAnswerTaskDto3;
    ChemFixedAnswerTaskDto chemFixedAnswerTaskDtoUpdate;
    ChemFixedAnswerTaskDto chemFixedAnswerTaskDto4;

    @Before
    public void prepareForTest() {
        //Создание ДТО для тестирования записи данных
        chemFixedAnswerTaskDto1 =
                new ChemFixedAnswerTaskDto("1", "111", "111", 1, 1);
        chemFixedAnswerTaskDto2 =
                new ChemFixedAnswerTaskDto("2", "222", "222", 2, 2);
        chemFixedAnswerTaskDto3 =
                new ChemFixedAnswerTaskDto("3", "333", "333", 3, 3);
        chemFixedAnswerTaskDtoUpdate =
                new ChemFixedAnswerTaskDto("3", "123", "123", 3, 3);
        chemFixedAnswerTaskDto4 =
                new ChemFixedAnswerTaskDto("4", "444", "444", 4, 4);
    }

    @Test
    public void contextLoad() throws Exception {
        mockMvc.perform(get("/chemFixedAnswer/v1.0/hello"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello!")));
    }

    @Test
    public void getAllTasks() throws Exception{
        mockMvc.perform(get("/chemFixedAnswer/v1.0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void getTaskById() throws Exception{
        mockMvc.perform(get("/chemFixedAnswer/v1.0/" + "2"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void saveNewTask() throws Exception {
         mockMvc.perform(post("/chemFixedAnswer/v1.0/")
                .content(objectMapper.writeValueAsString(chemFixedAnswerTaskDto1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }

    @Test
    public void updateTask() throws Exception {
        mockMvc.perform(put("/chemFixedAnswer/v1.0")
                .content(objectMapper.writeValueAsString(chemFixedAnswerTaskDtoUpdate))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

    }

    @Test
    public void deleteTask() throws Exception {
        mockMvc.perform(delete("/chemFixedAnswer/v1.0/" + "3"))
                .andExpect(status().is2xxSuccessful())
                .andDo(print());
    }
}


