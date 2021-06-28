package com.chemcool.school.tasks.chemEquation;



import com.chemcool.school.tasks.app.TasksApplication;
import com.chemcool.school.tasks.controllers.chemequations.ChemEquationsRestController;
import com.chemcool.school.tasks.infrastructure.storage.chemequations.ChemEquationsTaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
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
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class EquationControllerTest {


    private String expectedResponse;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ChemEquationsTaskRepository chemEquationTaskRepository;

    @Autowired
    private ChemEquationsRestController chemEquationsRestController;

    @Before
    public void cleanExpectedResponse() throws Exception {
        Thread.sleep(100);
    }

    @Test
    @DisplayName("Проверка контекста")
    public void contextTest() throws Exception {
        assertThat(chemEquationsRestController).isNotNull();
    }

    @Test
    @DisplayName("Достаем все задания")
    public void testGetAllChemEquationTask() throws Exception {
        mockMvc.perform(get("/chemEquations/v.1.0")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(10)));
    }

    @Test
    @DisplayName("Достаем все задания определенной главы")
    public void testGetAllChemEquationTaskByChapterId() throws Exception {
        int chapter = 3;

        mockMvc.perform(get("/chemEquations/v.1.0/chapter/" + chapter)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(3)));
//                .andExpect(jsonPath("$[*].chapterId").value(3));
    }

    @Test
    @DisplayName("Достаем все задания определенной главы и раздела")
    public void testGetAllChemEquationTaskByChapterIdAndReferenceId() throws Exception {
        int chapter = 3;
        int reference = 4;

        mockMvc.perform(get("/chemEquations/v.1.0/reference/" + chapter + "/" + reference)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", isA(ArrayList.class)))
                .andExpect(jsonPath("$.*", hasSize(1)));
    }

    @Test
    @DisplayName("Достаем 1 задание")
    public void testGetChemEquationTaskById() throws Exception {
        String id = "4";

        mockMvc.perform(get("/chemEquations/v.1.0/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskId").value(id))
                .andExpect(jsonPath("$.description").value("task"+id))
                .andExpect(jsonPath("$.chapterId").value(3))
                .andExpect(jsonPath("$.referenceId").value(4))
                .andExpect(jsonPath("$.taskType").value("equations"));
        System.out.println("\n" + expectedResponse + "\n");
    }

    @Test
    @DisplayName("Достаем случайное задание")
    public void testGetRandomChemEquationTask() throws Exception {
        mockMvc.perform(get("/chemEquations/v.1.0/randomTask")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskId",oneOf("10","9","8","7","6","5","4","3","2","1")));
    }

    @Test
    @DisplayName("Создаем задание задание")
    public void testCreateChemEquationTask() throws Exception {
        String id = mockMvc.perform(post("/chemEquations/v.1.0/")
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

        System.out.println("\n"+id+"\n");

//        mockMvc.perform(get("/equations/v.1.0/"+id)
//                .accept(MediaType.APPLICATION_JSON))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.taskId").value(id))
//                .andExpect(jsonPath("$.description").value("test"))
//                .andExpect(jsonPath("$.chapterId").value(1))
//                .andExpect(jsonPath("$.referenceId").value(1))
//                .andExpect(jsonPath("$.taskType").value("equations"));

    }

    @Test
    @DisplayName("Удаляем задание")
    public void testDelChemEquationTaskById() throws Exception {
        String id = "2";
        mockMvc.perform(delete("/chemEquations/v.1.0/" + id)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
        assertEquals(9,chemEquationTaskRepository.findAll().size());
    }

    @Test
    @DisplayName("Если введен полностью правильный ответ")
    public void testCheckAnswer1() throws Exception {
        String answer = "CuSO4+2NaOH→Cu(OH)2↓+Na2SO4";
        mockMvc.perform(post("/chemEquations/v.1.0/checkAnswer")
                .param("taskId","1")
                .param("userAnswer",answer)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answerResult").value(true))
                .andExpect(jsonPath("$.score").value(10));
    }

    @Test
    @DisplayName("Если введен полностью правильный ответ, но в другом порядке")
    public void testCheckAnswer2() throws Exception {
        String answer = "CuSO4+2NaOH→Na2SO4+Cu(OH)2↓";
        mockMvc.perform(post("/chemEquations/v.1.0/checkAnswer")
                .param("taskId","1")
                .param("userAnswer",answer)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answerResult").value(true))
                .andExpect(jsonPath("$.score").value(10));
    }

    @Test
    @DisplayName("Если введен не правельный правильный ответ")
    public void testCheckAnswer3() throws Exception {
        String answer = "badAnswer";
        mockMvc.perform(post("/chemEquationsv.1.0/checkAnswer")
                .param("taskId","1")
                .param("userAnswer",answer)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answerResult").value(false))
                .andExpect(jsonPath("$.score").value(0));
    }

    @Test
    @DisplayName("Если введен правильный ответ, но не учтен знак, агрегатное состояние и регистр")
    public void testCheckAnswer4() throws Exception {
        String answer = "cuso4+2naoh=cu(oh)2+na2so4";
        mockMvc.perform(post("/chemEquations/v.1.0/checkAnswer")
                .param("taskId","1")
                .param("userAnswer",answer)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answerResult").value(true))
                .andExpect(jsonPath("$.score").value(5));
    }

    @Test
    @DisplayName("Если введен полностью правильный ответ, кроме знака")
    public void testCheckAnswer5() throws Exception {
        String answer = "CuSO4+2NaOH=Cu(OH)2↓+Na2SO4";
        mockMvc.perform(post("/chemEquations/v.1.0/checkAnswer")
                .param("taskId","1")
                .param("userAnswer",answer)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answerResult").value(true))
                .andExpect(jsonPath("$.score").value(9));
    }

    @Test
    @DisplayName("Если введен полностью правильный ответ, кроме агрегатного состояния")
    public void testCheckAnswer6() throws Exception {
        String answer = "CuSO4+2NaOH→Cu(OH)2+Na2SO4";
        mockMvc.perform(post("/chemEquations/v.1.0/checkAnswer")
                .param("taskId","1")
                .param("userAnswer",answer)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answerResult").value(true))
                .andExpect(jsonPath("$.score").value(8));
    }

    @Test
    @DisplayName("Если введен полностью правильный ответ, кроме регистра")
    public void testCheckAnswer7() throws Exception {
        String answer = "cuso4+2naoh→cu(oh)2↓+na2so4";
        mockMvc.perform(post("/chemEquations/v.1.0/checkAnswer")
                .param("taskId","1")
                .param("userAnswer",answer)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answerResult").value(true))
                .andExpect(jsonPath("$.score").value(8));
    }

    @Test
    @DisplayName("Если введен полностью правильный ответ, кроме регистра и знака")
    public void testCheckAnswer8() throws Exception {
        String answer = "cuso4+2naoh=cu(oh)2↓+na2so4";
        mockMvc.perform(post("/chemEquations/v.1.0/checkAnswer")
                .param("taskId","1")
                .param("userAnswer",answer)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.answerResult").value(true))
                .andExpect(jsonPath("$.score").value(7));
    }

}
