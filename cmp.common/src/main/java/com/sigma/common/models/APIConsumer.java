package com.sigma.common.models;

/**
 * 有权限访问OAPI的消费方
 * @author XuGang
 *
 */
public class APIConsumer {
	private String id = null;
	private String secret;

	public APIConsumer(){
	}
	
	public APIConsumer(String id, String secret){
		this.id = id;
		this.secret = secret;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
}
