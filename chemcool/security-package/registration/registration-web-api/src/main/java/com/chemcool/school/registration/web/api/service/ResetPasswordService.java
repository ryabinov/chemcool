package com.chemcool.school.registration.web.api.service;

import com.chemcool.school.registration.domain.RegisterUser;
import com.chemcool.school.registration.domain.RegisterUserEventFactory;
import com.chemcool.school.registration.domain.RegisterUserEventType;
import com.chemcool.school.registration.exception.RegisterUserDefinitionException;
import com.chemcool.school.registration.repository.RegisterUserRepository;
import com.chemcool.school.registration.service.RegisterUserEventNotificationService;
import com.chemcool.school.registration.web.api.dto.ForgotPasswordDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@Service
public class ResetPasswordService {

    @Autowired
    RegisterUserEventNotificationService registerUserEventNotificationService;
    @Autowired
    private RegisterUserRepository repository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public String sendForgotPasswordEmail(ForgotPasswordDto forgotPasswordDto) {

        final String email = forgotPasswordDto.getEmail();
        final String token = UUID.randomUUID().toString();

        RegisterUser registerUser = repository.findByEmail(email);
        if (registerUser != null) {
            registerUser.setResetPasswordToken(token);
            repository.save(registerUser);
        } else {
            throw new RegisterUserDefinitionException("Пользователь с таким Email не найден " + email);
        }

        new Thread(() -> {
            try {
                String fromAddress = "borara3511@gmail.com";
                String senderName = "ChemCool";
                String subject = "Восстановление пароля на сайте ChemCool.ru";
                String content = "<p>Здравствуйте.</p>"
                        + "<p>Перейдите пожалуйста по ссылке ниже для сброса пароля:</p>"
                        + "<h3><a href=\"[[URL]]\" target=\"_self\">ВОССТАНОВИТЬ ПАРОЛЬ</a></h3>"
                        + "<br>"
                        + "<p>Игнорируйте это письмо если вы не запрашивали сброс пароля.</p>"
                        + "Рады, что Вы с нами!<br>"
                        + "ChemCool.ru";

                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message);

                helper.setFrom(fromAddress, senderName);
                helper.setTo(email);
                helper.setSubject(subject);

                String resetPasswordUrl = "http://localhost:3000/reset-password/" + token;

                content = content.replace("[[URL]]", resetPasswordUrl);

                helper.setText(content, true);
                mailSender.send(message);
            } catch (IOException | MessagingException e) {
                e.printStackTrace();
            }
        }).start();

        return registerUser.getId();
    }

    public void updatePassword(RegisterUser registerUser, String password) {

        registerUser.setPassword(passwordEncoder.encode(password));
        registerUser.setResetPasswordToken(null);
        log.info("Пользователь {} успешно изменил пароль", registerUser.getEmail());
        registerUserEventNotificationService.send(
                RegisterUserEventFactory.createEvent(registerUser, RegisterUserEventType.UPDATE)
        );
    }

}
