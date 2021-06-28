package com.chemcool.school.tasks.infrastructure.storage.chemmatches;

import com.chemcool.school.tasks.domain.chemmatches.ChemistryMatchingTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChemistryMatchingTaskRepository extends JpaRepository<ChemistryMatchingTask, String> {
    List<ChemistryMatchingTask> findByChapterId(String chapterId);
    List<ChemistryMatchingTask> getAllByChapterId(Integer chapterId);
}