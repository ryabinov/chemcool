package com.chemcool.school.answers.web.api.service;

import com.chemcool.school.answers.web.api.domain.EquationAnswerDto;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
public class CheckEquationService {
    private boolean[] result;
    private Set<String> bufRightAnswer;
    private Set<String> bufUserAnswer;
    private EquationAnswerDto answerDto;

//    Локальный тест
//    public static void main(String[] args) {
//        String trueAnswer = "CuSO4 + 2NaOH → Cu(OH)2↓+ Na2SO";
//        String userAnswer = "CuSO4 + 2NaOH → Na2SO4 + Cu(OH)2↓";
//        CheckEquationService checker = new CheckEquationService();
//
//        System.out.println(Arrays.toString(checker.checkAnswer(
//                trueAnswer.replaceAll(" ", ""),
//                userAnswer.replaceAll(" ", "")).getResultSet())
//        );
//    }

    public CheckEquationService() {
        this.result = new boolean[4];
        this.bufRightAnswer = new TreeSet();
        this.bufUserAnswer = new TreeSet();
        this.answerDto = new EquationAnswerDto();
    }

    public EquationAnswerDto checkAnswer(String rightAnswer, String userAnswer) {
        rightAnswer = rightAnswer.replaceAll(" ", "");
        userAnswer = userAnswer.replaceAll(" ", "");
        System.out.println(rightAnswer);
        System.out.println(userAnswer);

        if(result[0] = checkWithoutRegisterAndAgrStatus(rightAnswer, userAnswer)){
            answerDto.setResult(true);
            answerDto.addScore(5);
        }
        log.info("Проверка без учета знака агрегатного состояния и регистра: " + result[0]);

        if(result[1] = checkAgrStatus(rightAnswer, userAnswer)) {
            answerDto.addScore(2);
        }
        log.info("Проверка агрегатного состояния: " + result[1]);

        if(result[2] = checkRegister(rightAnswer, userAnswer)){
            answerDto.addScore(2);
        }
        log.info("Проверка регистра: " + result[2]);


        if(result[3] = checkSymbol(rightAnswer, userAnswer)){
            answerDto.addScore(1);
        }
        log.info("Проверка символа сравнения: " + result[3]);

        System.out.println(answerDto.getScore());
        answerDto.setResultSet(result);
        return answerDto;
    }

    //обнуляем буферные переменные
    private void bufClear() {
        bufUserAnswer.clear();
        bufRightAnswer.clear();
    }

    //проверка элементов без учета порядка
    private boolean check(String r, String u) {
        bufClear();
        bufRightAnswer.addAll(Arrays.asList(r.split("[→=⇄↔+]")));
        bufUserAnswer.addAll(Arrays.asList(u.split("[→=⇄↔+]")));
        return bufRightAnswer.equals(bufUserAnswer);
    }

    //проверка регистра
    private boolean checkRegister(String r, String u){
        r=r.replaceAll("[↓↑]", "");
        u=u.replaceAll("[↓↑]", "");
        return check(r,u);
    }

//    проверка агрегатного состояния
    private boolean checkAgrStatus(String r, String u) {
        return check(r,u)||check(r.toLowerCase(),u.toLowerCase());
//        bufClear();
//        bufRightAnswer.addAll(Arrays.asList(r.toLowerCase().split("[→=⇄↔+]")));
//        bufUserAnswer.addAll(Arrays.asList(u.toLowerCase().split("[→=⇄↔+]")));
//        return bufRightAnswer.equals(bufUserAnswer);
    }

    //без учета регистра и агрегатного состояния
    private boolean checkWithoutRegisterAndAgrStatus(String r, String u) {
        r=r.replaceAll("[↓↑]", "");
        u=u.replaceAll("[↓↑]", "");
        return check(r.toLowerCase(), u.toLowerCase());
    }


    //проверка вида реакции (знака между левой и правой частью уравнения)
    private boolean checkSymbol(String r, String u) {
        if(!checkWithoutRegisterAndAgrStatus(r,u)){
            return false;
        }
        r = r.replaceAll("[^→=⇄↔]", "");
        u = u.replaceAll("[^→=⇄↔]", "");
        return r.equals(u);
    }
}
