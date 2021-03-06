package com.sigma.common;

public class GenerateToken extends GenerateRandomString{
	
	private String token;

	public GenerateToken(String type, String id){
		this.token = getFirstTwoPart(type, id);
		this.token = this.token + getRandomString(16);
	}
	
	public GenerateToken(String type, String id, String str){
		this.token = getFirstTwoPart(type, id);
		this.token = this.token + getRandomString(16, str);
	}
	
	private String getFirstTwoPart(String type, String id){
		this.token = "";
		this.token = this.token + type + "#";
		
		String temp = "";
		if(id.length() < 6){
			for(int i=0;i<6-id.length();i++){
				temp += "0";
			}
			id = temp + id;
		}
		this.token = this.token + id + "#";
		
		return this.token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
