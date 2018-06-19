package com.mpm.sharding.routing.strategy;

import java.util.Collection;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;

import io.shardingsphere.core.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.core.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

public class DatePreciseShardingAlgorithm implements PreciseShardingAlgorithm<Date> {

	@Override
	public String doSharding(Collection<String> availableTargetNames, PreciseShardingValue<Date> shardingValue) {
		System.out.println("availableTargetNames:" + JSON.toJSONString(availableTargetNames) + ",shardingValue:"
				+ JSON.toJSONString(shardingValue));
		Date date = shardingValue.getValue();
		Assert.notNull(date, "date is null");
		String suffix = DateFormatUtils.format(date, "yyyyMM");
		String table = availableTargetNames.iterator().next() + "_" + suffix;
		System.out.println("table:"+table);
		return table;
	}

}
