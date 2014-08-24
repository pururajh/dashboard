package com.dashboard.vo;

import java.io.Serializable;

public class TrainingVo implements Serializable{
	private Integer idTraining;
	private String idTechnology;
	private String name;
	
	public Integer getIdTraining() {
		return idTraining;
	}
	public String getIdTechnology() {
		return idTechnology;
	}
	public String getName() {
		return name;
	}
	public void setIdTechnology(String idTechnology) {
		this.idTechnology = idTechnology;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setIdTraining(Integer idTraining) {
		this.idTraining = idTraining;
	}

}
