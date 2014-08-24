package com.dashboard.vo;

import java.io.Serializable;

public class TrainingAttaine implements Serializable {
	private int countOfEmployee;
	private String project;
	public int getCountOfEmployee() {
		return countOfEmployee;
	}
	public String getProject() {
		return project;
	}
	public void setCountOfEmployee(int countOfEmployee) {
		this.countOfEmployee = countOfEmployee;
	}
	public void setProject(String project) {
		this.project = project;
	}
}
