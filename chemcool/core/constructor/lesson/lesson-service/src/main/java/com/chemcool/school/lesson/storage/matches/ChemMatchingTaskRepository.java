package com.chemcool.school.lesson.storage.matches;

import com.chemcool.school.lesson.domain.matches.ChemMatchingTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChemMatchingTaskRepository extends JpaRepository<ChemMatchingTask, String>, JpaSpecificationExecutor<ChemMatchingTask> {
    List<ChemMatchingTask> getAllByChapterId(Integer chapterId);
    List<ChemMatchingTask> getAllByReferenceId(Integer referencesId);
    List<ChemMatchingTask> getAllByReferenceIdAndChapterId(int referenceId, int chapterId);
}