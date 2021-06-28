package com.chemcool.school.tasks.service.chemfixedanswer;



import com.chemcool.school.tasks.domain.chemfixedanswer.ChemFixedAnswerTask;
import com.chemcool.school.tasks.dto.chemfixedanswer.ChemFixedAnswerTaskDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChemFixedAnswerTaskPresentation {


    private final ChemFixedAnswerTaskServiceLayer serviceLayer;

    public List<ChemFixedAnswerTaskDto> getAllChemistryFixedAnswerDto(){
        return serviceLayer.getAllChemistryFixedAnswerDto();
    }

    public Optional<ChemFixedAnswerTask> getFixedAnswerTaskById(String id){
        return serviceLayer.getFixedAnswerTaskById(id);
    }

    public String createNewFixedAnswerTask(ChemFixedAnswerTaskDto taskDto){
        return serviceLayer.createNewFixedAnswerTask(taskDto);
    }

    public void updateFixedAnswerTask(ChemFixedAnswerTaskDto taskDto){
        serviceLayer.updateFixedAnswerTask(taskDto);
    }

    public void deleteFixedAnswerTask(String id){
        serviceLayer.deleteFixedAnswerTask(id);
    }
}
