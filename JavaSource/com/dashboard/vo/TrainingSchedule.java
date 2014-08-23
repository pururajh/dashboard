package com.dashboard.vo;

import java.io.Serializable;
import java.util.Date;

public class TrainingSchedule implements Serializable{
	private String trainingName;
	private Date startDate;
	private Date endDate;
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "TrainingSchedule [trainingName=" + trainingName
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
	
	

}
