package com.sigma.api.common.models;

public class Client {
	private int id;
	private String guid = "";
	private String location = "";
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Client(){
		
	}
	
	public Client(String guid){
		this.guid = guid;
	}
	
	public Client(int id, String guid){
		this.id = id;
		this.guid = guid;
	}
	
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
	public String getGUID() {
		return guid;
	}
	public void setGUID(String guid) {
		this.guid = guid;
	}
}
