package com.sigma.common.models;

import java.sql.Timestamp;

public class User {
 
	private int id;
	
	private String loginId = "~";
	
	private String nickName = "~";
	
	private Timestamp createTime;
	
	private String controlPassword = "";
	
	private String state = ResourceState.NORMAL.name();
	
	public User(){
	}
	
	public User(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return state;
	}
	
	public String getControlPassword() {
		return controlPassword;
	}

	public void setControlPassword(String controlPassword) {
		this.controlPassword = controlPassword;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public void setState(ResourceState state) {
		this.state = state.name();
	}
}

