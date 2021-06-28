package com.chemcool.school.answers;

import com.chemcool.school.answers.app.AnswersApplication;
import com.chemcool.school.answers.domain.equation.ChemEquationCorrectAswers;
import com.chemcool.school.answers.domain.fixedanswer.ChemFixedCorrectAnswers;
import com.chemcool.school.answers.domain.singleselect.ChemSingleSelectCorrectAnswers;
import com.chemcool.school.answers.domain.matches.ChemmathesCorrectAnswers;
import com.chemcool.school.answers.storage.*;
import com.chemcool.school.answers.domain.matches.CoupleForMatching;
import com.chemcool.school.answers.storage.equation.ChemEquationCorrectAnswersRepository;
import com.chemcool.school.answers.storage.fixedanswer.ChemFixedCorrectAnswersRepository;
import com.chemcool.school.answers.storage.matches.ChemmathesCorrectAnswersRepository;
import com.chemcool.school.answers.storage.singleselect.ChemSingleSelectCorrectAnswerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Перед запуском тестов поднять необходимо поднять сервис
 * аутентификации получить токен и вставить его в поле token
 */
@SpringBootTest(classes = AnswersApplication.class)
@Testcontainers
@AutoConfigureMockMvc
public class AnswersApplicationIntegrationTest extends RunTestcontainerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ChemEquationCorrectAnswersRepository chemEquationCorrectAnswersRepository;
    @Autowired
    private ChemFixedCorrectAnswersRepository chemFixedCorrectAnswersRepository;
    @Autowired
    private ChemmathesCorrectAnswersRepository chemmathesCorrectAnswersRepository;
    @Autowired
    private ChemSingleSelectCorrectAnswerRepository chemSingleSelectCorrectAnswerRepository;
    @Autowired
    private UserAnswersCorrectRepository userAnswersCorrectRepository;

    private ChemEquationCorrectAswers chemEquationСorrectAnswers =
            new ChemEquationCorrectAswers("c4e04c9b-test-equitation", "correctAnswerEquitation");

    private ChemFixedCorrectAnswers chemFixedCorrectAnswers =
            new ChemFixedCorrectAnswers("c4e04c9b-test-fixed", "correctAnswerFixed");

    private ChemmathesCorrectAnswers chemmathesCorrectAnswers = new ChemmathesCorrectAnswers("c4e04c9b-test-matches",
            List.of(new CoupleForMatching(1L, "leftCouple1", "rightCouple1"),
                    new CoupleForMatching(2L, "leftCouple2", "rightCouple2")));

    private ChemSingleSelectCorrectAnswers chemSingleSelectCorrectAnswers =
            new ChemSingleSelectCorrectAnswers("c4e04c9b-test-select", "correctAnswerSelect");

    private String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMYXpvdnNraTE5OTFAZ21haWwuY29tIiwibmljayI6Im5lbHNvbjkxIiwicm9sZSI6IlJPTEVfVVNFUl9CQVNFIiwidXNlcklkIjoiZTZjY2MxMzUtNGRjYi00MTMwLTkxYmQtZjZkYTQ0OWFiYjczIiwiZXhwIjoxNjE1MjQ3ODg4fQ.eEGYynv6EvRqggAatG3JyEKtIjWlmjFLdWgqYwHe1iTBdouEQ-U2Epv1hdKsX_ThFfmtjaRPoaM5OD7TzJI3Bg";

    @AfterEach
    void cleanDataBaseUserCorrect() {
        userAnswersCorrectRepository.deleteAll();
    }

    @Test
    @DisplayName("Правильный ответ на вопрос с фисированным ответом")
    void checkingCorrectAnswerTaskWithFixedAnswer() throws Exception {
        chemFixedCorrectAnswersRepository.save(chemFixedCorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-fixed")
                .param("taskType", "FIXED_ANSWER")
                .param("userAnswers", "correctAnswerFixed")
                .header("AuthorizationToken", token))
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("Не правильный ответ на вопрос с фисированным ответом")
    void checkingNoCorrectAnswerTaskWithFixedAnswer() throws Exception {
        chemFixedCorrectAnswersRepository.save(chemFixedCorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-fixed")
                .param("taskType", "FIXED_ANSWER")
                .param("userAnswers", "noCorrectAnswerFixed")
                .header("AuthorizationToken", token))
                .andExpect(content().string("false"));
    }

    @Test
    @DisplayName("Правильный ответ на вопрос с выбором одного ответа")
    void checkingCorrectAnswerTaskWithSingleSelectAnswer() throws Exception {
        chemSingleSelectCorrectAnswerRepository.save(chemSingleSelectCorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-select")
                .param("taskType", "SINGLE_SELECT")
                .param("userAnswers", "correctAnswerSelect")
                .header("AuthorizationToken", token))
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("Не правильный ответ на вопрос с выбором одного ответа")
    void checkingNoCorrectAnswerTaskWithSingleSelectAnswer() throws Exception {
        chemSingleSelectCorrectAnswerRepository.save(chemSingleSelectCorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-select")
                .param("taskType", "SINGLE_SELECT")
                .param("userAnswers", "noCorrectAnswerSelect")
                .header("AuthorizationToken", token))
                .andExpect(content().string("false"));
    }

    @Test
    @DisplayName("Правильный ответ на вопрос с хим уравнением")
    void checkingCorrectAnswerTaskWithEquitationAnswer() throws Exception {
        chemEquationCorrectAnswersRepository.save(chemEquationСorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-equitation")
                .param("taskType", "EQUATION")
                .param("userAnswers", "correctAnswerEquitation")
                .header("AuthorizationToken", token))
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("Не правильный ответ на вопрос с хим уравнением")
    void checkingNoCorrectAnswerTaskWithEquitationAnswer() throws Exception {
        chemEquationCorrectAnswersRepository.save(chemEquationСorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-equitation")
                .param("taskType", "EQUATION")
                .param("userAnswers", "NoCorrectAnswerEquitation")
                .header("AuthorizationToken", token))
                .andExpect(content().string("false"));
    }

    @Test
    @DisplayName("Правильный ответ на вопрос с сопоставлениями")
    void checkingCorrectAnswerTaskWithMatchesAnswer() throws Exception {
        chemmathesCorrectAnswersRepository.save(chemmathesCorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-matches")
                .param("taskType", "MATCHES")
                .header("AuthorizationToken", token)
                .content(objectMapper.writeValueAsString(List.of(new CoupleForMatching("leftCouple1", "rightCouple1"),
                        new CoupleForMatching("leftCouple2", "rightCouple2"))))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("Правильный ответ в другом порядке на вопрос с сопоставлениями")
    void checkingCorrectReversedAnswerTaskWithMatchesAnswer() throws Exception {
        chemmathesCorrectAnswersRepository.save(chemmathesCorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-matches")
                .param("taskType", "MATCHES")
                .header("AuthorizationToken", token)
                .content(objectMapper.writeValueAsString(List.of(new CoupleForMatching("leftCouple2", "rightCouple2"),
                        new CoupleForMatching("leftCouple1", "rightCouple1"))))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("true"));
    }

    @Test
    @DisplayName("Не правильный ответ с неправильными парами на вопрос с сопоставлениями")
    void checkingNoCorrectReversedAnswerTaskWithMatchesAnswer() throws Exception {
        chemmathesCorrectAnswersRepository.save(chemmathesCorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-matches")
                .param("taskType", "MATCHES")
                .header("AuthorizationToken", token)
                .content(objectMapper.writeValueAsString(List.of(new CoupleForMatching("leftCouple1", "rightCouple2"),
                        new CoupleForMatching("leftCouple2", "rightCouple1"))))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("false"));
    }

    @Test
    @DisplayName("Не правильный ответ на одну пару на вопрос с сопоставлениями")
    void checkingNoCorrectAnswerTaskWithMatchesAnswer() throws Exception {
        chemmathesCorrectAnswersRepository.save(chemmathesCorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-matches")
                .param("taskType", "MATCHES")
                .header("AuthorizationToken", token)
                .content(objectMapper.writeValueAsString(List.of(new CoupleForMatching("leftCouple1", "rightCouple1"),
                        new CoupleForMatching("leftCouple2", "rightCouple22"))))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("false"));
    }

    @Test
    @DisplayName("Проверка записи в бд правильного ответа пользователя")
    void checkingSaveCorrectAnswers() throws Exception {
        chemmathesCorrectAnswersRepository.save(chemmathesCorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-matches")
                .param("taskType", "MATCHES")
                .header("AuthorizationToken", token)
                .content(objectMapper.writeValueAsString(List.of(new CoupleForMatching("leftCouple1", "rightCouple1"),
                        new CoupleForMatching("leftCouple2", "rightCouple2"))))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string("true"));
        userAnswersCorrectRepository.findAll();
        assertThat(userAnswersCorrectRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Проверка одной записи в бд при правильном ответе пользователя несколько раз")
    void checkingOneSaveCorrectAnswers() throws Exception {
        chemEquationCorrectAnswersRepository.save(chemEquationСorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-equitation")
                .param("taskType", "EQUATION")
                .param("userAnswers", "correctAnswerEquitation")
                .header("AuthorizationToken", token))
                .andExpect(content().string("true"));

        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-equitation")
                .param("taskType", "EQUATION")
                .param("userAnswers", "correctAnswerEquitation")
                .header("AuthorizationToken", token))
                .andExpect(content().string("Задача уже была решена пользователем"));

        assertThat(userAnswersCorrectRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    @DisplayName("Не правильный ответ на вопрос не должен сохранятся в базу ответов")
    void checkingNoCorrectAnswerNoSaveBaseCorrectUserAnswers() throws Exception {
        chemEquationCorrectAnswersRepository.save(chemEquationСorrectAnswers);
        mockMvc.perform(post("/v1.0")
                .param("taskId", "c4e04c9b-test-equitation")
                .param("taskType", "EQUATION")
                .param("userAnswers", "NoCorrectAnswerEquitation")
                .header("AuthorizationToken", token))
                .andExpect(content().string("false"));

        assertThat(userAnswersCorrectRepository.findAll().size()).isEqualTo(0);
    }
}
