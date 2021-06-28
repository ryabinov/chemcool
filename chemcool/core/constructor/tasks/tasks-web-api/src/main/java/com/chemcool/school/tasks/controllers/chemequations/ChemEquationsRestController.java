package com.chemcool.school.tasks.controllers.chemequations;


import com.chemcool.school.tasks.dto.chemequations.ChemAnswerDto;
import com.chemcool.school.tasks.dto.chemequations.ChemEquationsTaskDto;
import com.chemcool.school.tasks.service.chemequations.ChemEquationsTaskPresentation;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/chemEquations/v.1.0")
@RequiredArgsConstructor
public class ChemEquationsRestController {

    @Autowired
    private final ChemEquationsTaskPresentation presentation;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Возвращает все сущности задания типа \"Уравнения\"")
    public List<ChemEquationsTaskDto> getAll() {
        return presentation.getAllChemistryEquationsDto();
    }

    @GetMapping("/chapter/{chapterId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Возвращает все сущности задания типа \"Уравнения\" по главе")
    public List<ChemEquationsTaskDto> getAllByChapterId(@PathVariable int chapterId) {
        return presentation.getAllChemistryEquationsByChapterIdDto(chapterId);
    }

    @GetMapping("/reference/{chapterId}/{referenceId}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Возвращает все сущности задания типа \"Уравнения\" по разделу и главе")
    public List<ChemEquationsTaskDto> getAllByReferenceId(@PathVariable int chapterId, @PathVariable int referenceId) {
        return presentation.getAllChemistryEquationsByChapterIdAndReferenceIdDto(chapterId, referenceId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Возвращает задание по ID типа \"Уравнения\"")
    public ChemEquationsTaskDto getEquationsTaskById(@PathVariable String id) {
        return presentation.getEquationsTaskById(id);
    }

    @GetMapping("/randomTask")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Возвращает случайную сущность задания типа \"Уравнения\"")
    public ChemEquationsTaskDto getRandomTask() {
        return presentation.getRandomChemistryEquationsDto();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Создает новую сущность задания типа \"Уравнения\"")
    public String saveNewEquationsTask(ChemEquationsTaskDto taskDto, String rightAnswer) {
        return presentation.createNewEquationsTask(taskDto, rightAnswer);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Обновляет существующую сущность задания типа \"Уравнения\"")
    public void saveEquationsTask(ChemEquationsTaskDto taskDto, String rightAnswer) {
        presentation.updateEquationsTask(taskDto, rightAnswer);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Удаляет существующую сущность задания типа \"Уравнения\"")
    public void deleteEquationsTask(@PathVariable String id) {
        presentation.deleteEquationsTask(id);
    }

    @PostMapping("/checkAnswer")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Проверяет введенный ответ с ответом в базе данных \"Уравнения\"")
    public ChemAnswerDto checkAnswer(String taskId, String userAnswer) {
        return presentation.checkAnswer(taskId, userAnswer);
    }
}