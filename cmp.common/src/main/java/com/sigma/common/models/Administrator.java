package com.sigma.common.models;

public class Administrator {
 
	private int id;
	
	private String name = "~";
	
	private String password = "~";
	
	private String supervisor = "FALSE";
	
	public Administrator(){
	}
	
	public Administrator(int id) {
		this.id = id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	
}

