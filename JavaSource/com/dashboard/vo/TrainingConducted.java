package com.dashboard.vo;

import java.io.Serializable;

public class TrainingConducted implements Serializable {
	
	private int numberOfEmployee;
	private String project;
	public int getNumberOfEmployee() {
		return numberOfEmployee;
	}
	public String getProject() {
		return project;
	}
	public void setNumberOfEmployee(int numberOfEmployee) {
		this.numberOfEmployee = numberOfEmployee;
	}
	public void setProject(String project) {
		this.project = project;
	}

}
