package com.chemcool.school.registration.web.api.controllers;

import com.chemcool.school.registration.domain.RegisterUser;
import com.chemcool.school.registration.exception.ApiResponse;
import com.chemcool.school.registration.exception.BadRequestException;
import com.chemcool.school.registration.repository.RegisterUserRepository;
import com.chemcool.school.registration.web.api.dto.ForgotPasswordDto;
import com.chemcool.school.registration.web.api.service.ResetPasswordService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/auth")
public class ResetPasswordController {

    @Autowired
    private ResetPasswordService resetPasswordService;
    @Autowired
    private RegisterUserRepository repository;

    @ApiOperation("отправляем ссылку сброса пароля на Email")
    @PostMapping("/forgot-password")
    public ResponseEntity<?> processForgotPassword(@Validated @RequestBody ForgotPasswordDto forgotPasswordDto) {

        if (!repository.existsByEmail(forgotPasswordDto.getEmail())) {
            throw new BadRequestException("Email адрес не был зарегистрирован!");
        }

        log.info("Вызван контроллер для сброса пароля на email: "
                + "[" + forgotPasswordDto.getEmail() + "]");

        String result = resetPasswordService.sendForgotPasswordEmail(forgotPasswordDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/")
                .buildAndExpand(result).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Письмо для сброса пароля успешно отправлено"));
    }

    @ApiOperation("Добавляем новый пароль пользователя")
    @PostMapping("/reset-password")
    public ResponseEntity<?> processResetPassword(@RequestBody ForgotPasswordDto forgotPasswordDto) {
        String token = forgotPasswordDto.getToken();
        String password = forgotPasswordDto.getPassword();

        RegisterUser registerUser = repository.findByResetPasswordToken(token);

        if (registerUser == null) {
            throw new BadRequestException("Неверная ссылка, либо истек срок действия ссылки");
        }

        resetPasswordService.updatePassword(registerUser, password);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/")
                .buildAndExpand(registerUser.getId()).toUri();

        return ResponseEntity.created(location)
                .body(new ApiResponse(true, "Пароль был успешно изменен!"));
    }
}
