package com.sigma.common.models;

public class CloumeMedia{

	private int id = 0;
	
	private String name = "";
	
	private String path = "";
	
	private String state = ResourceState.DISABLED.name();;
	
	private String version = "v0.0";
	
		
	public CloumeMedia(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CloumeMedia(int id){
		this.setId(id);
	}
	
	public CloumeMedia(int id, String name) {
		this.setId(id);
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getVersion() {
		return version;
	}

	public void setPosition(String version) {
		this.version = version;
	}

	public String getState() {
		return state;
	}
	
	public void setState(String state) {
		this.state = state;
	}
}
