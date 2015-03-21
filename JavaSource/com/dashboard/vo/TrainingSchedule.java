package com.dashboard.vo;

import java.io.Serializable;
import java.util.Date;

public class TrainingSchedule implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5083444257253107258L;
	private String trainingName;
	private Date startDate;
	private Date endDate;
	private Long id;
	private EmployeeVo tEmployee;
	private TrainingVo traing;
	
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
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EmployeeVo gettEmployee() {
		return tEmployee;
	}
	public void settEmployee(EmployeeVo tEmployee) {
		this.tEmployee = tEmployee;
	}
	public String getTrainingName() {
		return trainingName;
	}
	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}
	public TrainingVo getTraing() {
		return traing;
	}
	public void setTraing(TrainingVo traing) {
		this.traing = traing;
	}
	
	
	

}
