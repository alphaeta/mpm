package com.sunshine.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunshine.sample.service.FeignHelloService;
import com.sunshine.sample.service.HelloService;

@RestController
public class HelloControler {
	@Autowired
	HelloService helloService;
	
	@Autowired
    FeignHelloService feignHelloService;

	@RequestMapping(value = "/hi")
	public String hi(@RequestParam String name) {
		return helloService.hiService(name);
	}
	
	@RequestMapping(value = "/feignHi")
	public String feignHi(@RequestParam String name) {
		return feignHelloService.sayHi(name);
	}
	
	
}
