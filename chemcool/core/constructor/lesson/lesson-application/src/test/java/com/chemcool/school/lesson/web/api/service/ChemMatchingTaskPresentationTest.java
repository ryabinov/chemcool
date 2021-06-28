package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.matches.CoupleForMatching;
import com.chemcool.school.lesson.web.api.dto.ChemMatchingTaskDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class ChemMatchingTaskPresentationTest {

    @MockBean
    private  ChemMatchingTaskServiceLayer serviceLayer;

    @Autowired
    private ChemMatchingTaskPresentation presentation;

    private final List<ChemMatchingTaskDto> taskList = new ArrayList<>();

    private String id;
    private Integer i, referenceId, chapterId;

    @BeforeEach
    void setUp() {
        List<CoupleForMatching> coupleForMatching = new ArrayList<>();
        coupleForMatching.add(new CoupleForMatching(1L, "He", "Гелий"));
        coupleForMatching.add(new CoupleForMatching(2L, "H", "Водород"));
        coupleForMatching.add(new CoupleForMatching(3L, "O", "Кислород"));
        coupleForMatching.add(new CoupleForMatching(4L, "C", "Углерод"));

        taskList.add(new ChemMatchingTaskDto(
                "id1",
                "description1",
                1,
                1,
                coupleForMatching,
                "Matches"
        ));
        taskList.add(new ChemMatchingTaskDto(
                "id2",
                "description2",
                1,
                2,
                coupleForMatching,
                "Matches"
        ));
        taskList.add(new ChemMatchingTaskDto(
                "id3",
                "description3",
                2,
                2,
                coupleForMatching,
                "Matches"
        ));
        i = 1;
        id = taskList.get(i).getTaskId();
        referenceId = 2;
        chapterId = 1;
    }

    @Test
    void getTaskDtoByIdDto() {
        Mockito.when(serviceLayer.getTaskDtoByIdDto(id)).thenReturn(taskList.get(i));
        ChemMatchingTaskDto task = presentation.getTaskDtoByIdDto(id);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("description"+ (i+1));
        assertThat(task.getTaskId()).isEqualTo(id);
        assertThat(task.getTaskType()).isEqualTo("Matches");
    }

    @Test
    void getTaskDtoByFakeIdDto() {
        Mockito.when(serviceLayer.getTaskDtoByIdDto(id)).thenReturn(taskList.get(0));
        ChemMatchingTaskDto task = presentation.getTaskDtoByIdDto(id+1);
        assertThat(task).isNull();
    }

    @Test
    void getAllTasksDto() {
        Mockito.when(serviceLayer.getAllTasksDto()).thenReturn(taskList);
        List<ChemMatchingTaskDto> taskExamples = presentation.getAllTasksDto();
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(3);
        for (ChemMatchingTaskDto task : taskExamples) {
            assertThat(task.getTaskId()).isNotNull();
            assertThat(task.getChapterId()).isNotNull();
            assertThat(task.getReferenceId()).isNotNull();
            assertThat(task.getTaskType()).isEqualTo("Matches");
        }
    }

    @Test
    void getAllTasksByReferenceIdDto() {
        Mockito.when(serviceLayer.getAllTasksByReferenceIdDto(referenceId)).thenReturn(taskList.subList(1, 3));
        List<ChemMatchingTaskDto> taskExamples = presentation.getAllTasksByReferenceIdDto(referenceId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemMatchingTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getTaskType()).isEqualTo("Matches");
        }
    }

    @Test
    void getAllTasksByChapterIdDto() {
        Mockito.when(serviceLayer.getAllTasksByChapterIdDto(chapterId)).thenReturn(taskList.subList(0, 2));
        List<ChemMatchingTaskDto> taskExamples = presentation.getAllTasksByChapterIdDto(chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemMatchingTaskDto task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("Matches");
        }
    }

    @Test
    void getAllTasksByReferenceIdAndChapterIdDto() {
        Mockito.when(serviceLayer.getAllTasksByReferenceIdAndChapterIdDto(referenceId, chapterId)).thenReturn(taskList.subList(1, 2));
        List<ChemMatchingTaskDto> taskExamples = presentation.getAllTasksByReferenceIdAndChapterIdDto(referenceId, chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(1);
        for (ChemMatchingTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("Matches");
        }
    }
}