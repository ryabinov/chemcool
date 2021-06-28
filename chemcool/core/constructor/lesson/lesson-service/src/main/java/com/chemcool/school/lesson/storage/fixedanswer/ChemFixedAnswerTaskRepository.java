package com.chemcool.school.lesson.storage.fixedanswer;

import com.chemcool.school.lesson.domain.fixedanswer.ChemFixedAnswerTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChemFixedAnswerTaskRepository extends JpaRepository<ChemFixedAnswerTask, String>, JpaSpecificationExecutor<ChemFixedAnswerTask> {
    List<ChemFixedAnswerTask> getAllByChapterId(int chapterId);
    List<ChemFixedAnswerTask> getAllByReferenceId(int referenceId);
    List<ChemFixedAnswerTask> getAllByReferenceIdAndChapterId(int referenceId, int chapterId);
}
