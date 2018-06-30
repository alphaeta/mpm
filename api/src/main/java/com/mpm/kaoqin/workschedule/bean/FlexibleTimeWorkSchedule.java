package com.mpm.kaoqin.workschedule.bean;

import java.util.Calendar;

import io.swagger.annotations.ApiModelProperty;

/**
 * 自由排班（时间不限）
 * 
 * @author Administrator
 *
 */
public class FlexibleTimeWorkSchedule extends AbstractFixedLocationWorkSchedule {
	/**
	 * @see Calendar
	 * @see Calendar#SUNDAY
	 * @see Calendar#MONDAY
	 * @see Calendar#TUESDAY
	 * @see Calendar#WEDNESDAY
	 * @see Calendar#THURSDAY
	 * @see Calendar#FRIDAY
	 * @see Calendar#SATURDAY
	 */
	@ApiModelProperty(value = "考勤星期", required = true)
	private int[] daysOfWeek;

	public FlexibleTimeWorkSchedule() {
		super();
		setType(this.getClass().getCanonicalName());
	}
}
