package com.mpm.kaoqin.workschedule.bean;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 固定地点
 * 
 * @author Administrator
 *
 */
public class AbstractFixedLocationWorkSchedule extends AbstractWorkSchedule {
	@ApiModelProperty(value = "定位地址", required = true)
	private String locationName;
	@ApiModelProperty(value = "维度", required = true)
	private float latitude;
	@ApiModelProperty(value = "经度", required = true)
	private float longitude;
	@ApiModelProperty(value = "偏差", required = true)
	private long offset;
	@ApiModelProperty(value = "开始签到时间", required = true)
	private Date startSignTime;

	public Date getStartSignTime() {
		return startSignTime;
	}

	public void setStartSignTime(Date startSignTime) {
		this.startSignTime = startSignTime;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public long getOffset() {
		return offset;
	}

	public void setOffset(long offset) {
		this.offset = offset;
	}

}
