package com.chemcool.school.tasks.infrastructure.storage.chemfixedanswer;

import com.chemcool.school.tasks.domain.chemfixedanswer.ChemFixedAnswerTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChemFixedAnswerTaskRepository extends JpaRepository<ChemFixedAnswerTask, String>, JpaSpecificationExecutor<ChemFixedAnswerTask> {
    List<ChemFixedAnswerTask> findAllByChapterId(int chapterId);
}
