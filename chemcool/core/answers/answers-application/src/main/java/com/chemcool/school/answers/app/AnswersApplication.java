package com.chemcool.school.answers.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(
        scanBasePackages = {"com.chemcool.school.answers"}
)
@EntityScan("com.chemcool.school.answers")
@EnableJpaRepositories("com.chemcool.school.answers")
@EnableEurekaClient
public class AnswersApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnswersApplication.class, args);
    }
}
