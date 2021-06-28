package com.chemcool.school.tasks.controllers.chemsingleselect;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringCloudConfigServerTestController {

    @Value("${spring.cloud.config.test.message: " +
            "Это тестовое сообщение для проверки работы Spring Cloud Config Server храниться в аннотации @Value прямо в контроллере.}")
    private String message;

    @GetMapping("/cloud-config-server-test-message")
    @ApiOperation("Проверка работы Spring Cloud Config Server.")
    String getMessage() {
        return this.message;
    }
}