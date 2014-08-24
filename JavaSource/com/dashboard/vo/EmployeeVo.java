package com.dashboard.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class EmployeeVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7715921254375623523L;
	private int id;
	private String firstName;
	private String lastName;
	private ProjectVo idProject;
	private Float tcsExperience;
	private Float overalExperience;
	private Timestamp joiningDate;
	private Timestamp jobStartDate;
	
	
	
	private int empId;
	private String empName;
	private String projName;
	private String tcsExp;
	private String overalExp;


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Timestamp getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Timestamp joiningDate) {
		this.joiningDate = joiningDate;
	}
	public Timestamp getJobStartDate() {
		return jobStartDate;
	}
	public void setJobStartDate(Timestamp jobStartDate) {
		this.jobStartDate = jobStartDate;
	}
	
	

	
	public Float getTcsExperience() {
		
		long diff = System.currentTimeMillis()- this.joiningDate.getTime();
		
		long diffYears = diff / (60 * 60 * 1000 * 24 * 365);
		System.out.println("tcs Experience"+diffYears);
		return tcsExperience;
	}
	public void setTcsExperience(Float tcsExperience) {
		this.tcsExperience = tcsExperience;
	}
	public Float getOveralExperience() {
		return overalExperience;
	}
	public void setOveralExperience(Float overalExperience) {
		this.overalExperience = overalExperience;
	}
	
	@Override
	public String toString() {
		return "EmployeeVo ["+" firstName="
				+ firstName + ", lastName=" + lastName + "]";
	}
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
	public String getTcsExp() {
		return tcsExp;
	}
	public void setTcsExp(String tcsExp) {
		this.tcsExp = tcsExp;
	}
	public String getOveralExp() {
		return overalExp;
	}
	public void setOveralExp(String overalExp) {
		this.overalExp = overalExp;
	}
	public ProjectVo getIdProject() {
		return idProject;
	}
	public void setIdProject(ProjectVo idProject) {
		this.idProject = idProject;
	}
	
	
	
	
	

}
