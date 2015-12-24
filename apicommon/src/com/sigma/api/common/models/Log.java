package com.sigma.api.common.models;

import java.sql.Timestamp;

public class Log {
	private int userId;
	private int appId;
	private Timestamp time;
	private String content = "";
	
	public Log(){
		
	}
	
	public Log(int userId, int appId, Timestamp time, String content){
		this.appId = appId;
		this.userId = userId;
		this.time = time;
		this.content = content;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
	
}
