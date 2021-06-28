package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.domain.equation.ChemEquationsTask;
import com.chemcool.school.lesson.service.equation.ChemEquationsTaskProxyService;
import com.chemcool.school.lesson.web.api.dto.ChemEquationsTaskDto;
import com.chemcool.school.lesson.web.api.exeption.ChemTaskEmptyException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<ChemEquationsTaskDto> getAllChemEquationsByReferenceIdDto(int referenceId) {
        List<ChemEquationsTaskDto> list = new ArrayList<>();
        for (ChemEquationsTask task : proxyService.getAllByReferenceId(referenceId)) {
            list.add(new ChemEquationsTaskDto(task));
        }
        return list;
    }

    public List<ChemEquationsTaskDto> getAllByReferenceIdAndChapterIdDto(int referenceId, int chapterId) {
        List<ChemEquationsTaskDto> list = new ArrayList<>();
        for (ChemEquationsTask task : proxyService.getAllByReferenceIdAndChapterId(referenceId, chapterId)) {
            list.add(new ChemEquationsTaskDto(task));
        }
        return list;
    }

    public ChemEquationsTaskDto getChemEquationsTaskById(String id) {
        ChemEquationsTask task = proxyService.getById(id)
                .orElseThrow(() -> new ChemTaskEmptyException("Задание не найдено."));
        return new ChemEquationsTaskDto(task);
    }


}
