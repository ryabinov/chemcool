package com.chemcool.school.lesson.web.api.service;

import com.chemcool.school.lesson.domain.fixedanswer.ChemFixedAnswerTask;
import com.chemcool.school.lesson.service.fixedanswer.ChemFixedAnswerTaskProxyService;
import com.chemcool.school.lesson.web.api.dto.ChemFixedAnswerTaskDto;
import com.chemcool.school.lesson.web.api.exeption.ChemTaskEmptyException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChemFixedAnswerTaskServiceLayer {

    private final ChemFixedAnswerTaskProxyService proxyService;

    public List<ChemFixedAnswerTaskDto> getAllChemistryFixedAnswerDto () {
        List<ChemFixedAnswerTaskDto> list = new ArrayList<>();
        for(ChemFixedAnswerTask task : proxyService.getAll()){
            list.add(new ChemFixedAnswerTaskDto(task));
        }
        return list;
    }
    public List<ChemFixedAnswerTaskDto> getAllChemistryFixedAnswerByReferenceIdDto (int referenceId) {
        List<ChemFixedAnswerTaskDto> list = new ArrayList<>();
        for(ChemFixedAnswerTask task : proxyService.getAllByReferenceId(referenceId)){
            list.add(new ChemFixedAnswerTaskDto(task));
        }
        return list;
    }

    public List<ChemFixedAnswerTaskDto> getAllChemistryFixedAnswerByChapterIdDto (int chapterId) {
        List<ChemFixedAnswerTaskDto> list = new ArrayList<>();
        for(ChemFixedAnswerTask task : proxyService.getAllByChapterId(chapterId)){
            list.add(new ChemFixedAnswerTaskDto(task));
        }
        return list;
    }

    public List<ChemFixedAnswerTaskDto> getAllChemistryFixedAnswerByReferenceIdAndChapterIdDto (int referenceId, int chapterId) {
        List<ChemFixedAnswerTaskDto> list = new ArrayList<>();
        for(ChemFixedAnswerTask task : proxyService.getAllByReferenceIdAndChapterId(referenceId, chapterId)){
            list.add(new ChemFixedAnswerTaskDto(task));
        }
        return list;
    }

    public ChemFixedAnswerTaskDto getFixedAnswerTaskById(String id) {
        ChemFixedAnswerTask task = proxyService.getById(id).orElseThrow(
                () -> new ChemTaskEmptyException("Задание не найдено."));
        return new ChemFixedAnswerTaskDto(task);
    }

}
