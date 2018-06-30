package com.mpm.kaoqin.workschedule.bean;
/**
 * 固定排班
 * @author Administrator
 *
 */

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "固定排班")
public class FixedTimeWorkSchedule extends FlexibleTimeWorkSchedule {
	@ApiModelProperty(value = "工作时间段", required = true)
	private List<TimeSection> workTimeSections;

	@ApiModelProperty(value = "午休时间段", required = true)
	private TimeSection freeTimeSection;

	public List<TimeSection> getWorkTimeSections() {
		return workTimeSections;
	}

	public void setWorkTimeSections(List<TimeSection> workTimeSections) {
		this.workTimeSections = workTimeSections;
	}

	public TimeSection getFreeTimeSection() {
		return freeTimeSection;
	}

	public void setFreeTimeSection(TimeSection freeTimeSection) {
		this.freeTimeSection = freeTimeSection;
	}

	public FixedTimeWorkSchedule() {
		super();
		setType(FIXED_TIME_WORK_SCHEDULE);
	}
}
