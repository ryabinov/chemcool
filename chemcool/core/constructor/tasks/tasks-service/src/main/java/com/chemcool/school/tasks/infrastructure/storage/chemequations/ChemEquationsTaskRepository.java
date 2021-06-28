package com.chemcool.school.tasks.infrastructure.storage.chemequations;

import com.chemcool.school.tasks.domain.chemequations.ChemEquationsTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChemEquationsTaskRepository
        extends JpaRepository<ChemEquationsTask, String>, JpaSpecificationExecutor<ChemEquationsTask> {
    List<ChemEquationsTask> findAllByChapterId(int chapterId);
    List<ChemEquationsTask> findAllByChapterIdAndReferenceId(int chapterId,int referenceId);
}
