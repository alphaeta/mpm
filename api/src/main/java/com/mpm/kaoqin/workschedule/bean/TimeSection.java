package com.mpm.kaoqin.workschedule.bean;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class TimeSection {
	@ApiModelProperty(value = "开始时间")
	private Date start;
	@ApiModelProperty(value = "结束时间")
	private Date end;

	public TimeSection() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}
