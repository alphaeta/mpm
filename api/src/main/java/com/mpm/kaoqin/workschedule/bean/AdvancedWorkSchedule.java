package com.mpm.kaoqin.workschedule.bean;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 高级排班(多个固定排班)
 * 
 * @author Administrator
 *
 */
@ApiModel(value="高级排班")
public class AdvancedWorkSchedule extends AbstractWorkSchedule {
	@ApiModelProperty(value = "时间段配置", required = true)
	private List<FixedTimeWorkSchedule> schedules;

	public List<FixedTimeWorkSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<FixedTimeWorkSchedule> schedules) {
		this.schedules = schedules;
	}

	public AdvancedWorkSchedule() {
		super();
		setType(this.getClass().getCanonicalName());
	}

}
