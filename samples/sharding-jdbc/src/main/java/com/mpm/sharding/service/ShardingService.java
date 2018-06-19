package com.mpm.sharding.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpm.sharding.entity.ShardingOrder;
import com.mpm.sharding.repository.ShardingOrderRepository;
@Service
public class ShardingService {
	@Autowired
	private ShardingOrderRepository orderRepository;
	
	public void demo() {
		//orderRepository.createIfNotExistsTable();
		System.out.println("Insert--------------");
		ShardingOrder order = new ShardingOrder();
		order.setCompanyId("b");
		order.setTime(new Date());
		order.setStatus("tt");
		
		orderRepository.insert(order);
		

	}
}
