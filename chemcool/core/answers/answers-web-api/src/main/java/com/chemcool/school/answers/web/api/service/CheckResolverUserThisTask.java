package com.chemcool.school.answers.web.api.service;

import com.chemcool.school.answers.domain.UserAnswersCorrect;
import com.chemcool.school.answers.service.UserAnswersCorrectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CheckResolverUserThisTask {
    private final UserAnswersCorrectService userAnswersCorrectService;

    public boolean checkScoreTasks(String taskId, String userId){
        List<UserAnswersCorrect> allUserAnswerCorrect = userAnswersCorrectService.getAllUserAnswerCorrectByUserId(userId);
        for (UserAnswersCorrect iter : allUserAnswerCorrect) {
            if(iter.getCorrectAnswersId().equals(taskId) && iter.getScore()==10){
                return true;
            }
        }
        return false;
    }

    public boolean checkResolverUserThisTask(String taskId, String userId) {
        List<String> allUserResolvedTaskId = userAnswersCorrectService.getAllUserResolvedTaskId(userId);
        boolean flag = false;
        for (String iter : allUserResolvedTaskId) {
           flag = iter.equals(taskId);
        }
        return flag;
    }
}
