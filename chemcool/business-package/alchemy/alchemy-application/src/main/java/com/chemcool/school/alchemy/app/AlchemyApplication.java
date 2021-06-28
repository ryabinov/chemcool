package com.chemcool.school.alchemy.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(
		scanBasePackages = {"com.chemcool.school.alchemy"}
)
@EnableEurekaClient
public class AlchemyApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlchemyApplication.class, args);
	}

}
