package com.chemcool.school.tasks.service.chemequations;


import com.chemcool.school.tasks.domain.chemequations.ChemEquationsTaskEvent;


/**
 * Сервис уведомления о событии с соединениями
 *
 * @version 1.0
 * @autor Евгений Жиленков
 */
public interface ChemEquationsTaskEventNotificationService {
    void send(ChemEquationsTaskEvent event);
}
