package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.app.LessonApplication;
import com.chemcool.school.lesson.domain.matches.ChemMatchingTask;
import com.chemcool.school.lesson.domain.matches.ChemMatchingTaskExample;
import com.chemcool.school.lesson.domain.matches.CoupleForMatching;
import com.chemcool.school.lesson.service.matches.ChemMatchingTaskProxyService;
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
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = LessonApplication.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
class ChemMatchingTaskServiceLayerTest {

    @MockBean
    private ChemMatchingTaskProxyService proxyService;

    @Autowired
    private ChemMatchingTaskServiceLayer serviceLayer;

    private final List<ChemMatchingTask> taskList = new ArrayList<>();

    private String id;
    private Integer i, referenceId, chapterId;

    @BeforeEach
    void setUp() {
        List<CoupleForMatching> coupleForMatching = new ArrayList<>();
        coupleForMatching.add(new CoupleForMatching(1L, "He", "Гелий"));
        coupleForMatching.add(new CoupleForMatching(2L, "H", "Водород"));
        coupleForMatching.add(new CoupleForMatching(3L, "O", "Кислород"));
        coupleForMatching.add(new CoupleForMatching(4L, "C", "Углерод"));

        taskList.add(ChemMatchingTask.createChemistryMatchingTask(new ChemMatchingTaskExample(
                "id1",
                "description1",
                1,
                1,
                coupleForMatching
        )));
        taskList.add(ChemMatchingTask.createChemistryMatchingTask(new ChemMatchingTaskExample(
                "id2",
                "description2",
                1,
                2,
                coupleForMatching
        )));
        taskList.add(ChemMatchingTask.createChemistryMatchingTask(new ChemMatchingTaskExample(
                "id3",
                "description3",
                2,
                2,
                coupleForMatching
        )));
        i = 0;
        id = taskList.get(i).getTaskId();
        referenceId = 2;
        chapterId = 1;
    }

    @Test
    void getTaskDtoByIdDto() {
        Mockito.when(proxyService.getById(id)).thenReturn(Optional.ofNullable(taskList.get(i)));
        ChemMatchingTaskDto task = serviceLayer.getTaskDtoByIdDto(id);
        assertThat(task).isNotNull();
        assertThat(task.getDescription()).isEqualTo("description"+ (i+1));
        assertThat(task.getTaskId()).isEqualTo(id);
        assertThat(task.getTaskType()).isEqualTo("Matches");
    }

    @Test
    void getTaskDtoByFakeIdDto() {
        Mockito.when(proxyService.getById(id)).thenReturn(Optional.ofNullable(taskList.get(0)));
        ChemMatchingTaskDto task = serviceLayer.getTaskDtoByIdDto(id+1);
        assertThat(task).isNull();
    }

    @Test
    void getAllTasksDto() {
        Mockito.when(proxyService.getAll()).thenReturn(taskList);
        List<ChemMatchingTaskDto> taskExamples = serviceLayer.getAllTasksDto();
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
        Mockito.when(proxyService.getAllByReferenceId(referenceId)).thenReturn(taskList.subList(1, 3));
        List<ChemMatchingTaskDto> taskExamples = serviceLayer.getAllTasksByReferenceIdDto(referenceId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemMatchingTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getTaskType()).isEqualTo("Matches");
        }
    }

    @Test
    void getAllTasksByChapterIdDto() {
        Mockito.when(proxyService.getAllByChapterId(chapterId)).thenReturn(taskList.subList(0, 2));
        List<ChemMatchingTaskDto> taskExamples = serviceLayer.getAllTasksByChapterIdDto(chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(2);
        for (ChemMatchingTaskDto task : taskExamples) {
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("Matches");
        }
    }

    @Test
    void getAllTasksByReferenceIdAndChapterIdDto() {
        Mockito.when(proxyService.getAllByReferenceIdAndChapterId(referenceId, chapterId)).thenReturn(taskList.subList(1, 2));
        List<ChemMatchingTaskDto> taskExamples = serviceLayer.getAllTasksByReferenceIdAndChapterIdDto(referenceId, chapterId);
        assertThat(taskExamples).isNotNull();
        assertThat(taskExamples).hasSize(1);
        for (ChemMatchingTaskDto task : taskExamples) {
            assertThat(task.getReferenceId()).isEqualTo(referenceId);
            assertThat(task.getChapterId()).isEqualTo(chapterId);
            assertThat(task.getTaskType()).isEqualTo("Matches");
        }
    }
}