package com.sigma.common.models;

public class ClientType {
 
	private int id;
	
	private String clientName = "~";
	
	private String type = "~";
	
	
	private String state = ResourceState.NORMAL.name();
	
	public ClientType(){
	}
	
	public ClientType(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public void setState(ResourceState state) {
		this.state = state.name();
	}
}

