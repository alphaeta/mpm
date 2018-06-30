package com.mpm.kaoqin.workschedule.bean;

import io.swagger.annotations.ApiModelProperty;
/**
 * 排班
 * @author Administrator
 *
 */
public abstract class AbstractWorkSchedule {
	@ApiModelProperty(value = "排班类型")
	public String type;
	
	public TimeSection timeSection;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
