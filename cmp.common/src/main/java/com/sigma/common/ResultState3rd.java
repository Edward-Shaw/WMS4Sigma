package com.sigma.common;

public class ResultState3rd extends ResultState{
	private int result3rd = -99;
	private String message3rd = "unknown message";
	
	public ResultState3rd(){
		
	}
	
	public ResultState3rd(int state, String message, int result3rd, String message3rd){
		super(state, message);
		this.setResult3rd(result3rd);
		this.setMessage3rd(message3rd);
	}
	
	public int getResult3rd() {
		return result3rd;
	}
	public void setResult3rd(int result3rd) {
		this.result3rd = result3rd;
	}
	public String getMessage3rd() {
		return message3rd;
	}
	public void setMessage3rd(String message3rd) {
		this.message3rd = message3rd;
	}
	
	
}
