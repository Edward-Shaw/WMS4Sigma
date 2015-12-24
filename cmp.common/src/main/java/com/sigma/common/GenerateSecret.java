package com.sigma.common;

public class GenerateSecret extends GenerateRandomString{
	
	private String secret;
	
	public GenerateSecret(int length){
		this.secret = getRandomString(length);
	}
	
	public GenerateSecret(int length, String str){
		this.secret = getRandomString(length, str);
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
}
