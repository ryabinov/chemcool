package com.chemcool.school.theory.service;

import com.chemcool.school.theory.app.TheoryApplication;
import com.chemcool.school.theory.domain.ChemistryTheoryEvent;
import com.chemcool.school.theory.domain.ChemistryTheoryEventType;
import com.chemcool.school.theory.storage.ChemistryTheoryEventJournal;
import com.chemcool.school.theory.web.api.dto.TheoryDto;
import com.chemcool.school.theory.web.api.service.ChemistryTheoryPresentation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TheoryApplication.class)
@Testcontainers
@AutoConfigureMockMvc
public class ChemistryTheoryTest extends RunTestcontainerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ChemistryTheoryPresentation presentation;
    @Autowired
    private ChemistryTheoryEventJournal journal;

    public TheoryDto theoryDtoForTest =
            new TheoryDto("1", "theoryName", "theoryDescription", 1, 1);

    public String BASE_URL = "/v1.0/theory";

    @Test
    void createLessonExampleTest() throws Exception {
        mockMvc.perform(post(BASE_URL + "/create")
                .content(objectMapper.writeValueAsString(theoryDtoForTest))
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void updateLessonExampleTest() throws Exception {
        mockMvc.perform(put(BASE_URL + "/update")
                .content(objectMapper.writeValueAsString(theoryDtoForTest))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void deleteLessonExampleTest() throws Exception {
        mockMvc.perform(delete(BASE_URL + "/delete")
                .content(objectMapper.writeValueAsString(theoryDtoForTest))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void getTheoryExampleById() throws Exception {
        addingTestTheoryDataBase(theoryDtoForTest);
        String id = theoryDtoForTest.getTheoryDtoId();

        mockMvc.perform(
                get(BASE_URL + "/getBy/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.theoryId").value(id))
                .andExpect(jsonPath("$.theoryName").value("theoryName"));
    }

    @Test
    void checkSaveEventWithDataBase() {
        addingTestTheoryDataBase(theoryDtoForTest);
        ChemistryTheoryEvent theoryEvent = journal.findAll().get(0);

        assertThat(theoryEvent.getEventId()).isNotBlank();
        assertThat(theoryEvent.getEventAuthor()).isNotBlank();
        assertThat(theoryEvent.getEventOccurringContext()).isEqualTo("ChemistryTheoryEvent");
        assertThat(theoryEvent.getEventOccurringContextTime()).isNotNull();
        assertThat(theoryEvent.getEventType()).isEqualTo(String.valueOf(ChemistryTheoryEventType.CREATED));
        assertThat(theoryEvent.getVersion()).isNotBlank();
        assertThat(theoryEvent.getEventPayload()).isNotNull();
        assertThat(theoryEvent.getEventEntityId()).isEqualTo(theoryEvent.getEventPayload().getTheoryId());
    }

    public void addingTestTheoryDataBase(TheoryDto theoryDto) {
        presentation.createChemistryTheoryDto(theoryDto);
    }
}

