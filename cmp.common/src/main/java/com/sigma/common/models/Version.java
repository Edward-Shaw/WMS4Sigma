package com.sigma.common.models;

import java.sql.Timestamp;
public class Version {
	private int id = 0;
	private String type = "";
	private String apiId = "";
	private String version = "v0.0";
	private String path = "~";
	private Timestamp createTime;
	private String state = ResourceState.DISABLED.name();
	private String md5 = "";
	
	public Version(){
		
	}
	
	public Version(String type, String apiId, String version, String path, Timestamp createTime, String state){
		this.type = type;
		this.apiId = apiId;
		this.version = version;
		this.path = path;
		this.createTime = createTime;
		this.state = state;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getApiId() {
		return apiId;
	}

	public void setApiId(String apiId) {
		this.apiId = apiId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

}

