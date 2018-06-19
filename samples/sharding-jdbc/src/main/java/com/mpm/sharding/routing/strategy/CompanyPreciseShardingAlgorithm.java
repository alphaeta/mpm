package com.mpm.sharding.routing.strategy;

import java.util.Collection;

import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.mpm.sharding.cfg.Constants;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

public class CompanyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String> {

	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<String> shardingValue) {
		System.out.println("availableTargetNames:" + JSON.toJSONString(availableTargetNames) + ",shardingValue:" + JSON.toJSONString(shardingValue));
		String companyKey = shardingValue.getValue();
		String db = Constants.COM_DB_MAP.get(companyKey);
		Assert.notNull(db, companyKey+"not found db");
		System.out.println("db:"+db);
		return db;
	}

}
