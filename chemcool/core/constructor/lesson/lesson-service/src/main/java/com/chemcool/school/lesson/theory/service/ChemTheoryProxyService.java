package com.chemcool.school.lesson.theory.service;

import com.chemcool.school.lesson.theory.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChemTheoryProxyService {
    private final ChemTheoryEventNotificationService notificationService;
    private final ChemTheoryPageService pageService;

    public String add(ChemTheoryExample example) {
        ChemTheory theory = ChemTheoryFactory.createTheory(example);
        notificationService.send(
                ChemTheoryEventFactory.createEvent(theory, ChemTheoryEventType.CREATED)
        );
        return theory.getTheoryId();
    }

    public void delete(ChemTheory theory){
        pageService.delete(theory);
    }

    public void update(ChemTheory theory){
        notificationService.send(
                ChemTheoryEventFactory.createEvent(theory, ChemTheoryEventType.UPDATED)
        );
    }

    public List<ChemTheory> getAll() {return pageService.getAll();}

    public List<ChemTheory> getAllByChapterId(int chapterId){
                return pageService.getAllByChapterId(chapterId);
    }

    public List<ChemTheory> getAllByReferenceId(int referenceId){
                return pageService.getAllByReferenceId(referenceId);
    }

    public List<ChemTheory> getAllByReferenceIdAndChapterId(int referenceId, int chapterId){
        return pageService.getAllByReferenceIdAndChapterId(referenceId, chapterId);
    }

    public Optional<ChemTheory> getById(String theoryId) {

        return pageService.getAllByTheoryId(theoryId);
    }
}

