package com.chemcool.school.lesson.service.equation;

import com.chemcool.school.lesson.domain.equation.ChemEquationsTaskEvent;


/**
 * Сервис уведомления о событии с соединениями
 *
 * @version 1.0
 * @autor Евгений Жиленков
 */
public interface ChemEquationsTaskEventNotificationService {
    void send(ChemEquationsTaskEvent event);
}
