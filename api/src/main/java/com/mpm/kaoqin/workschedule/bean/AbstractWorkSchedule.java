package com.mpm.kaoqin.workschedule.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 排班
 * 
 * @author Administrator
 *
 */
public abstract class AbstractWorkSchedule {
	@ApiModelProperty(value = "排班类型")
	private String type;
	@ApiModelProperty(value = "有效时间区间")
	private TimeSection timeSection;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public TimeSection getTimeSection() {
		return timeSection;
	}

	public void setTimeSection(TimeSection timeSection) {
		this.timeSection = timeSection;
	}
}
