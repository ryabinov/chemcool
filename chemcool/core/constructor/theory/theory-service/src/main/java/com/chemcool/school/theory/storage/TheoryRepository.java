package com.chemcool.school.theory.storage;

import com.chemcool.school.theory.domain.ChemistryTheory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface TheoryRepository extends JpaRepository<ChemistryTheory, UUID>, JpaSpecificationExecutor<ChemistryTheory> {

    String tableName = "chemistry_page";

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE " + tableName + " SET " +
                    " theory_chapter = :theoryChapter, " +
                    " theory_name = :theoryName, " +
                    " theory_description = :theoryDescription, " +
                    " theory_references = :theoryReferences " +
                    " WHERE theory_id = :theoryId",
            nativeQuery = true
    )
    void updateTheory(
            @Param("theoryId") String theoryId,
            @Param("theoryName") String theoryName,
            @Param("theoryChapter") int theoryChapter,
            @Param("theoryDescription") String theoryDescription,
            @Param("theoryReferences") int theoryReferences
    );

    ChemistryTheory findByTheoryId(String theoryId);


}
