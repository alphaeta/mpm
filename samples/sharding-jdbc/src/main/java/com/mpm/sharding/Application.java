package com.mpm.sharding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mpm.sharding.cfg.Constants;
import com.mpm.sharding.cfg.RouteConfig;
import com.mpm.sharding.repository.ShardingOrderRepository;
import com.mpm.sharding.service.DemoService;
import com.mpm.sharding.service.ShardingService;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private DemoService demoService;
	@Autowired
	private RouteConfig config;
	@Autowired
	private ShardingService shardingService;
	
	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Constants.COM_DB_MAP=config.getComroute();
		System.out.println(Constants.COM_DB_MAP);
		//demoService.demo();
		shardingService.demo();
	}
}
