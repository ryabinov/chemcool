package com.chemcool.school.tasks.infrastructure.storage.chemsingleselect;

import com.chemcool.school.tasks.domain.chemsingleselect.ChemSingleSelectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChemSingleSelectTaskRepository extends JpaRepository<ChemSingleSelectTask, String> {
    List<ChemSingleSelectTask> getAllByChapterId(int chapterId);
}
