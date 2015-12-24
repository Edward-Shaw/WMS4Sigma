package com.sigma.api.common.models;

public class SimpleUser extends PublicResource{
	
	private int id = 0;
	private String name = "";
	
	public SimpleUser(){}
	
	public SimpleUser(int id, String name){
		this.id = id;
		this.name = name;
	}
	
	public SimpleUser(String key, String name){
	    super(key);
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
