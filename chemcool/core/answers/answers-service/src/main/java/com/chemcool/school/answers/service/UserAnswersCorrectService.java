package com.chemcool.school.answers.service;

import com.chemcool.school.answers.domain.UserAnswersCorrect;
import com.chemcool.school.answers.storage.UserAnswersCorrectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserAnswersCorrectService {
    private final UserAnswersCorrectRepository userAnswersCorrectRepository;

    public void saveUserCorrectAnswers(UserAnswersCorrect userAnswersCorrect) {
        userAnswersCorrectRepository.save(userAnswersCorrect);
    }

    public void delUserCorrectAnswers(String taskId, String userId){
        userAnswersCorrectRepository.deleteByCorrectAnswersIdAndUserId(taskId,userId);
    }

    public List<UserAnswersCorrect> getAllUserAnswerCorrectByUserId(String userId){
        return userAnswersCorrectRepository.getAllByUserId(userId);
    }

    public List<String> getAllUserResolvedTaskId(String userId) {
        List<String> userResolvedTaskId = new ArrayList<>();
        List<UserAnswersCorrect> allUserResolvedTaskId = userAnswersCorrectRepository.getAllUserResolvedTaskId(userId);
        for (UserAnswersCorrect iter : allUserResolvedTaskId) {
            userResolvedTaskId.add(iter.getCorrectAnswersId());
        }
        return userResolvedTaskId;
    }
}
