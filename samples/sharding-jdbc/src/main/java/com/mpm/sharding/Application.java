package com.mpm.sharding;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.mpm.sharding.cfg.Constants;
import com.mpm.sharding.service.DemoService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	
	
	@Bean
	@ConfigurationProperties(prefix = "company2dbmap")
	public Map<String, String> com2dbMap() {
		return new HashMap<String, String>();
	}

	public static void main(String[] args) {
		try (ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args)) {
			
			applicationContext.getBean(DemoService.class).demo();
		}
	}

	@Override
	public void run(String... args) throws Exception {
		Constants.COM_DB_MAP=com2dbMap();
		System.out.println(Constants.COM_DB_MAP);
	}
}
