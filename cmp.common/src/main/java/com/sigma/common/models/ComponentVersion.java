package com.sigma.common.models;

public class ComponentVersion {
	private int id = 0;
	private int versionId = 0;
	private String componentType = "";
	private int componentId = 0;
	private String updatable = "TRUE";
	private Version currentVersion;
	private Version latestVersion;
	
	public ComponentVersion(){
		
	}
	
	public ComponentVersion(int versionId, String componentType, int componentId){
		this.versionId = versionId;
		this.componentType = componentType;
		this.componentId = componentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	public String getComponentType() {
		return componentType;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public int getComponentId() {
		return componentId;
	}

	public void setComponentId(int componentId) {
		this.componentId = componentId;
	}

	public Version getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Version currentVersion) {
		this.currentVersion = currentVersion;
	}
	
	public Version getLatestVersion() {
		return latestVersion;
	}

	public void setLatestVersion(Version latestVersion) {
		this.latestVersion = latestVersion;
	}

	public String getUpdatable() {
		return updatable;
	}

	public void setUpdatable(String updatable) {
		this.updatable = updatable;
	}
}
