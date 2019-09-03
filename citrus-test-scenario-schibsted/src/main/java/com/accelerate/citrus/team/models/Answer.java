package com.accelerate.citrus.team.models;

import java.io.Serializable;

public class Answer implements Serializable {
	private String id;
	private String teamName;
	private String answer;
	
	public Answer() {
		
	}
	
	public Answer(String id, String teamName, String answer) {
		this.id = id;
		this.answer= answer;
		this.teamName = teamName;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
