package com.sigma.common;

public class ResultState {
	private int state;
//	private int code;
	private String message;
	
	public ResultState(){
	}
	
	public ResultState(int state){
		this.state = state;
	}
	
	public ResultState(int state, String message){
		this.state = state;
		this.message = message;
	}
	
/*	public ResultState(int code, String message){
		this.code = code;
		this.message = message;
	}*/

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}
/*
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}*/

	public void setMessage(String message) {
		this.message = message;
	}
}
