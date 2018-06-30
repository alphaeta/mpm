package com.mpm.kaoqin.workschedule.bean;

import java.util.List;

/**
 * 高级排班(多个固定排班)
 * 
 * @author Administrator
 *
 */
public class AdvancedWorkSchedule extends AbstractWorkSchedule {
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
