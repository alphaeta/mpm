package com.sunshine.sample.service;

import org.springframework.stereotype.Component;

@Component
public class FeignHelloServiceHiHystric implements FeignHelloService {

	@Override
	public String sayHi(String name) {
		// TODO Auto-generated method stub
		return "hi," + name + ",sorry, Feign error!";
	}

}
