package com.mpm.sharding;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.mpm.sharding.service.DemoService;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		try (ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args)) {
			applicationContext.getBean(DemoService.class).demo();
		}
	}
}
