package com.chemcool.school.registration.web.api.service;

import com.chemcool.school.registration.domain.RegisterUser;
import com.chemcool.school.registration.domain.RegisterUserEventFactory;
import com.chemcool.school.registration.domain.RegisterUserEventType;
import com.chemcool.school.registration.repository.RegisterUserRepository;
import com.chemcool.school.registration.service.RegisterUserEventNotificationService;
import com.chemcool.school.registration.web.api.dto.RegisterUserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class VerificationEmailService {

    @Autowired
    private RegisterUserRepository repository;
    @Autowired
    RegisterUserEventNotificationService registerUserEventNotificationService;
    @Autowired
    private RegisterUserPresentation registerUserPresentation;
    @Autowired
    private JavaMailSender mailSender;

    public String sendVerificationEmail(RegisterUserDto user) {

        final String token = UUID.randomUUID().toString();
        user.setVerificationCode(token);
        user.setEnabled(false);
        new Thread(() -> {
            try {
                String toAddress = user.getEmail();
                String fromAddress = "borara3511@gmail.com";
                String senderName = "ChemCool";
                String subject = "Подтвердите регистрацию на ChemCool.ru";
                String content = "Уважаемый <b> [[name]]</b>,<br>"
                        + "Для активации аккаунта перейдите по ссылке ниже:<br>"
                        + "<h3><a href=\"[[URL]]\" target=\"_self\">АКТИВИРОВАТЬ</a></h3>"
                        + "код подтверждения: <b> [[token]]</b><br><br>"   //TODO используется для дебага в swagger,удалить
                        + "Рады, что Вы с нами!<br>"
                        + "ChemCool.ru";

                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);

                helper.setFrom(fromAddress, senderName);
                helper.setTo(toAddress);
                helper.setSubject(subject);

                String verifyURL = "http://localhost:3000/verify-email/" + user.getVerificationCode();

                content = content.replace("[[name]]", user.getFullName());
                content = content.replace("[[URL]]", verifyURL);
                content = content.replace("[[token]]", token);

                helper.setText(content, true);
                mailSender.send(message);
            } catch (IOException | MessagingException e) {
                e.printStackTrace();
            }
        }).start();
        log.info("Письмо для подтверждения аккаунта отправлено");

        return registerUserPresentation.add(user);
    }

    public boolean verify(String verificationCode) {
        RegisterUser user = repository.findByVerificationCode(verificationCode);

        if (user == null || user.isEnabled()) {
            return false;
        } else {

            user.setVerificationCode(null);
            user.setEnabled(true);
            registerUserEventNotificationService.send(
                    RegisterUserEventFactory.createEvent(user, RegisterUserEventType.UPDATE)
            );
            log.info("Аккаунт {} подтвержден пользователем", user.getEmail());
            return true;
        }
    }
}
