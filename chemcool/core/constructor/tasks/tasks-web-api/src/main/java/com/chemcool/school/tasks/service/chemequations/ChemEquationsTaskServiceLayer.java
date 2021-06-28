package com.chemcool.school.tasks.service.chemequations;

import com.chemcool.school.tasks.chemequations.ChemTaskEmptyException;
import com.chemcool.school.tasks.domain.chemequations.ChemEquationsTask;
import com.chemcool.school.tasks.domain.chemequations.ChemEquationsTaskExample;
import com.chemcool.school.tasks.dto.chemequations.ChemEquationsTaskDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Сервис прослойка нижнего уровня содержащий бизнес логику CRUD операций
 * с полученным в ходе вычисления уравнения результатом
 *
 * @version 1.0
 * @autor Иван Полещук
 */
@Service
@AllArgsConstructor
public class ChemEquationsTaskServiceLayer {

    private final ChemEquationsTaskProxyService proxyService;

    public List<ChemEquationsTaskDto> getAllChemEquationsDto() {
        List<ChemEquationsTaskDto> list = new ArrayList<>();
        for (ChemEquationsTask task : proxyService.getAll()) {
            list.add(new ChemEquationsTaskDto(task));
        }
        return list;
    }

    public List<ChemEquationsTaskDto> getAllChemEquationsByChapterIdDto(int chapterId) {
        List<ChemEquationsTaskDto> list = new ArrayList<>();
        for (ChemEquationsTask task : proxyService.getAllByChapterId(chapterId)) {
            list.add(new ChemEquationsTaskDto(task));
        }
        return list;
    }

    public List<ChemEquationsTaskDto> getAllChemEquationsByChapterIdAndReferenceIdDto(int chapterId, int referenceId) {
        List<ChemEquationsTaskDto> list = new ArrayList<>();
        for (ChemEquationsTask task : proxyService.getAllByChapterIdAndReferenceId(chapterId, referenceId)) {
            list.add(new ChemEquationsTaskDto(task));
        }
        return list;
    }

    public ChemEquationsTaskDto getRandomChemistryEquationsDto() {
        List<ChemEquationsTaskDto> list = new ArrayList<>();
        for (ChemEquationsTask task : proxyService.getAll()) {
            list.add(new ChemEquationsTaskDto(task));
        }
        return list.get(new Random().nextInt(list.size()));
    }

    public ChemEquationsTaskDto getChemEquationsTaskById(String id) {
        ChemEquationsTask task = proxyService.getById(id)
                .orElseThrow(() -> new ChemTaskEmptyException("Уравнение не найдено."));
        return new ChemEquationsTaskDto(task);
    }


    public String createNewChemEquationsTask(ChemEquationsTaskDto taskDto, String rightAnswer) {
        return proxyService.add(
                new ChemEquationsTaskExample(
                        taskDto.getDescription(),
                        rightAnswer.replaceAll(" ", ""),
                        taskDto.getChapterId(),
                        taskDto.getReferenceId()
                )
        );
    }

    public void updateChemEquationsTask(ChemEquationsTaskDto taskDto, String rightAnswer) {
        proxyService.update(
                new ChemEquationsTask(
                        taskDto.getTaskId(),
                        taskDto.getDescription(),
                        rightAnswer.replaceAll(" ", ""),
                        taskDto.getChapterId(),
                        taskDto.getReferenceId()
                )
        );
    }

    public void deleteChemEquationsTask(String id) {
        proxyService.deleteById(id);
    }

    public boolean[] checkAnswer(String taskId, String userAnswer) {
        return proxyService.checkAnswer(taskId, userAnswer.replaceAll(" ", ""));
    }
}
