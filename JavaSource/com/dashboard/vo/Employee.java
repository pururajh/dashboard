package com.dashboard.vo;

import java.io.Serializable;

public class Employee implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3843098976496339210L;
	private int empId;
	private String empName;
	private String projName;
	private Float tcsExp;
	private Float OveralExp;
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}

	public Float getTcsExp() {
		return tcsExp;
	}
	public void setTcsExp(Float tcsExp) {
		this.tcsExp = tcsExp;
	}
	public Float getOveralExp() {
		return OveralExp;
	}
	public void setOveralExp(Float overalExp) {
		OveralExp = overalExp;
	}
	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", projName=" + projName
				+ ", tcsExp=" + tcsExp + ", OveralExp=" + OveralExp + "]";
	}
	
	
	
	
	

}
