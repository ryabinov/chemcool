package com.chemcool.school.answers.storage;

import com.chemcool.school.answers.domain.UserAnswersCorrect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserAnswersCorrectRepository extends JpaRepository<UserAnswersCorrect, Long> {

    List<UserAnswersCorrect> getAllByUserId(String userId);

    void deleteByCorrectAnswersIdAndUserId(String taskId, String userId);

    @Query("SELECT m FROM UserAnswersCorrect m WHERE m.userId=:userId")
    List<UserAnswersCorrect> getAllUserResolvedTaskId(@Param("userId") String userId);
}