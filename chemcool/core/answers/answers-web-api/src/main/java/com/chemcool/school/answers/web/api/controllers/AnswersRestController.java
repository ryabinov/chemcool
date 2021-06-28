package com.chemcool.school.answers.web.api.controllers;

import com.chemcool.school.answers.domain.UserAnswersCorrect;
import com.chemcool.school.answers.service.UserAnswersCorrectService;
import com.chemcool.school.answers.domain.matches.CoupleForMatching;
import com.chemcool.school.answers.web.api.domain.AnswerDto;
import com.chemcool.school.answers.web.api.domain.TaskType;
import com.chemcool.school.answers.web.api.service.CheckResolverUserThisTask;
import com.chemcool.school.answers.web.api.service.CheckUserAnswersService;
import com.chemcool.school.answers.web.api.service.UserIdentJwtParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1.0")
@RequiredArgsConstructor
public class AnswersRestController {

    private final CheckUserAnswersService checkUserAnswersService;
    private final UserIdentJwtParser jwtParser;
    private final UserAnswersCorrectService userAnswersCorrectService;
    private final CheckResolverUserThisTask checkResolverUserThisTask;
    private final ObjectMapper objectMapper;

    @PostMapping
    @ApiOperation("Возвращает AnswerDto в ответ пользователю")
    public ResponseEntity<String> checkAnswerUser(@RequestParam String taskId, @RequestParam TaskType taskType,
                                                  @RequestParam(required = false) String userAnswers,
                                                  @RequestBody(required = false) List<CoupleForMatching> coupleForMatchingList,
                                                  @RequestHeader(value = "AuthorizationToken") String token) throws JsonProcessingException {

        String userId = jwtParser.getIdUserOfToken(token);
        AnswerDto userAnswersDto;

        System.out.println("id-> "+userId);
        if (checkResolverUserThisTask.checkScoreTasks(taskId, userId)) {
            System.out.println("sdfghjksdfghj");
            return new ResponseEntity<>("Задача уже была решена пользователем", HttpStatus.OK);
        }


        if (userAnswers != null) {
            userAnswersDto = checkUserAnswersService.checkUserAnswer(taskId, taskType, userAnswers);
        }
        else {
            userAnswersDto = checkUserAnswersService.checkUserAnswer(taskId, coupleForMatchingList);
        }
        UserAnswersCorrect userAnswersCorrect = new UserAnswersCorrect(
                userId,
                jwtParser.getEmailUserOfToken(token),
                taskId,
                userAnswersDto.getScore());

        if (userAnswersDto.isResult()) {
            userAnswersCorrectService.delUserCorrectAnswers(taskId,userId);
            userAnswersCorrectService.saveUserCorrectAnswers(userAnswersCorrect);
        }
        return new ResponseEntity<>(objectMapper.writeValueAsString(userAnswersDto), HttpStatus.OK);
    }
}
