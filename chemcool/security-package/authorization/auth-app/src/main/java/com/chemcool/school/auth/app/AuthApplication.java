package com.chemcool.school.auth.app;

import com.chemcool.school.auth.service.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.chemcool.school.auth"})
@EntityScan("com.chemcool.school.auth")
@EnableJpaRepositories("com.chemcool.school.auth")
@EnableEurekaClient
@EnableConfigurationProperties(AppProperties.class)
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class, args);
    }
}
