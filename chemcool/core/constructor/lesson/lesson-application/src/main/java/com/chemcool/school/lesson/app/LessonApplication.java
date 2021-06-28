package com.chemcool.school.lesson.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(
        scanBasePackages = {"com.chemcool.school.lesson"}
)
@EntityScan("com.chemcool.school.lesson")
@EnableJpaRepositories("com.chemcool.school.lesson")
@EnableEurekaClient
public class LessonApplication {
    public static void main(String[] args) {
        SpringApplication.run(LessonApplication.class, args);
    }
}
