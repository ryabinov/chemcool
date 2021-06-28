package com.chemcool.school.tasks.controllers.chemsingleselect;

import com.chemcool.school.tasks.dto.chemsingleselect.ChemistrySingleSelectTaskDto;
import com.chemcool.school.tasks.service.chemsingleselect.ChemistrySingleSelectTaskPresentation;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chemistrySingleSelect/v1.0")
@RequiredArgsConstructor
public class ChemistrySingleSelectTaskRestController {

    private final ChemistrySingleSelectTaskPresentation presentation;

    @GetMapping
    @ApiOperation("Возвращает все задания типа \"Выбор одного ответа\" по химии.")
    public List<ChemistrySingleSelectTaskDto> getAllTasks() {
        return presentation.getAllTasks();
    }

    @GetMapping("/{uuid}")
    @ApiOperation("Возвращает дто сущности задания типа \"Выбор одного ответа\" по химии по UUID.")
    public ChemistrySingleSelectTaskDto getTaskById(@PathVariable String uuid) {
        return presentation.getTaskDtoById(uuid);
    }

    @PostMapping
    @ApiOperation("Создает новую сущность задания типа \"Выбор одного ответа\" по химии.")
    @ResponseBody
    public String createNewTask(@RequestBody ChemistrySingleSelectTaskDto task) {
        return presentation.add(task);
    }

    @PutMapping
    @ApiOperation("Сохраняет существующую сущность задания типа \"Выбор одного ответа\" по химии.")
    @ResponseBody
    public String updateTask(@RequestBody ChemistrySingleSelectTaskDto task) {
        presentation.update(task);
        return "Update task UUID: " + task.getTaskDtoId();
    }

    @DeleteMapping("/{uuid}")
    @ApiOperation("Удалеят существующую сущность задания типа \"Выбор одного ответа\" по химии по UUID.")
    public String deleteTask(@PathVariable String uuid) {
        presentation.deleteById(uuid);
        return "Сущность с UUID = " + uuid + " была успешно удалена.";
    }

}
