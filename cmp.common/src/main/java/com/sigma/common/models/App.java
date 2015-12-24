package com.sigma.common.models;


public class App extends Product{
		
	private String manual = "";
	
	private String screenshot = "";
	
	private String path = "";
	
	private String version = "v0.0";
	
	private String md5 = "";
	
	private String downloadPath = "";
	
	public App() {
	}
	
	public App(String id){
		this.setId(id);
	}
	
	public App(String name, double price, String id){
		this.setName(name);
		this.setPrice(price);
		this.setId(id);
	}

	public String getManual() {
		return manual;
	}

	public void setManual(String manual) {
		this.manual = manual;
	}

	public String getScreenshot() {
		return screenshot;
	}

	public void setScreenshot(String screenshot) {
		this.screenshot = screenshot;
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

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public String getDownloadPath() {
		return downloadPath;
	}

	public void setDownloadPath(String downloadPath) {
		this.downloadPath = downloadPath;
	}
	
}
