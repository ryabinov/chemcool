package com.chemcool.school.theory.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {"com.chemcool.school.theory"}
)
@EntityScan("com.chemcool.school.theory")
@EnableJpaRepositories("com.chemcool.school.theory")
@EnableEurekaClient
public class TheoryApplication {
    public static void main(String[] args) {
        SpringApplication.run(TheoryApplication.class, args);
    }
}
