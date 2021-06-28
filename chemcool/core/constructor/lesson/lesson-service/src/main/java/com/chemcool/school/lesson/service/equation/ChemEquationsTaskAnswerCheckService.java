package com.chemcool.school.lesson.service.equation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@Service
public class ChemEquationsTaskAnswerCheckService {
    private boolean[] result;
    private Set<String> bufRightAnswer;
    private Set<String> bufUserAnswer;

    public ChemEquationsTaskAnswerCheckService() {
        this.result = new boolean[4];
        this.bufRightAnswer = new TreeSet();
        this.bufUserAnswer = new TreeSet();
    }

    public boolean[] checkAnswer(String rightAnswer, String userAnswer) {

        result[0] = checkRegister(rightAnswer, userAnswer);
        log.info("Проверка без учета регистра: " + result[0]);

        result[1] = check(rightAnswer, userAnswer);
        log.info("Проверка регистра" + result[1]);

        result[2] = checkAgrStatus(rightAnswer, userAnswer);
        log.info("Проверка c учетом агрегатного состояния: " + result[2]);

        result[3] = checkSymbol(rightAnswer, userAnswer);
        log.info("Проверка символа сравнения: " + result[3]);

        return result;
    }

    //обнуляем буферные переменные
    private void bufClear() {
        bufUserAnswer.clear();
        bufRightAnswer.clear();
    }

    //проверка элементов без учета порядка
    private boolean check(String r, String u) {
        bufClear();
        bufRightAnswer.addAll(Arrays.asList(r.replaceAll("[↓↑]", "").split("[→=⇄↔+]")));
        bufUserAnswer.addAll(Arrays.asList(u.replaceAll("[↓↑]", "").split("[→=⇄↔+]")));
        return bufRightAnswer.equals(bufUserAnswer);
    }

    //без учета регистра
    private boolean checkRegister(String r, String u) {
        return check(r.toLowerCase(), u.toLowerCase());
    }

    //проверка с учетом агрегатного состояния продуктов реакции
    private boolean checkAgrStatus(String r, String u) {
        bufClear();
        bufRightAnswer.addAll(Arrays.asList(r.toLowerCase().split("[→=⇄↔+]")));
        bufUserAnswer.addAll(Arrays.asList(u.toLowerCase().split("[→=⇄↔+]")));
        return bufRightAnswer.equals(bufUserAnswer);
    }

    //проверка вида реакции (знака между левой и правой частью уравнения)
    private boolean checkSymbol(String r, String u) {
        r = r.replaceAll("[^→=⇄↔]", "");
        u = u.replaceAll("[^→=⇄↔]", "");
        return r.equals(u);
    }
}
