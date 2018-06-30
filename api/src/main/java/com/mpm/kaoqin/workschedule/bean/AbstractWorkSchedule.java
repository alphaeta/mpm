package com.mpm.kaoqin.workschedule.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * 排班
 * 
 * @author Administrator
 *
 */
public abstract class AbstractWorkSchedule {
	public static int FIXED_TIME_WORK_SCHEDULE = 0;
	public static int FLEXIBLE_TIME_WORK_SCHEDULE = 1;
	public static int ADVANCED_WORK_SCHEDULE = 2;

	@ApiModelProperty(value = "排班类型")
	private int type;
	@ApiModelProperty(value = "有效时间区间")
	private TimeSection timeSection;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public TimeSection getTimeSection() {
		return timeSection;
	}

	public void setTimeSection(TimeSection timeSection) {
		this.timeSection = timeSection;
	}
}
