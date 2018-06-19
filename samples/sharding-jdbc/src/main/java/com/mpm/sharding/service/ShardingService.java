package com.mpm.sharding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpm.sharding.repository.ShardingOrderRepository;
@Service
public class ShardingService {
	@Autowired
	private ShardingOrderRepository orderRepository;
	
	public void demo() {
		System.out.println("Insert--------------");

	}
}
