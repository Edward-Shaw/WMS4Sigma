package com.sigma.common.models;

import java.sql.Timestamp;

import com.sigma.common.LongToTimeString;

public class AppInstance {
    private int id = 0;
	private App app = null;
	private User user = null;
	private String appM = null;
	private String clientAddress = "";
	private int clientPort = 0;
	private int serverPort = 0;
	private String clientType = "";
	private String remoteControlAddress = "";
	private int remoteControlPort= 0;
	private String resolution = "*";
	private Timestamp beginTime;
	private String sessionId = "";
	private long serviceTime = 0;
	@SuppressWarnings("unused")
	private String serviceTimeString = "";
	
	public AppInstance(){
		
	}
	public AppInstance(int id){
		this.id= id;
	}

	public int getId() {
		return id;
	}
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAppM() {
		return appM;
	}
	public void setAppM(String appM) {
		this.appM = appM;
	}
	public String getClientAddress() {
		return clientAddress;
	}
	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	public int getClientPort() {
		return clientPort;
	}
	public void setClientPort(int clientPort) {
		this.clientPort = clientPort;
	}
	public int getServerPort() {
		return serverPort;
	}
	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
		this.clientType = clientType;
	}
	public String getRemoteControlAddress() {
		return remoteControlAddress;
	}
	public void setRemoteControlAddress(String remoteControlAddress) {
		this.remoteControlAddress = remoteControlAddress;
	}
	public int getRemoteControlPort() {
		return remoteControlPort;
	}
	public void setRemoteControlPort(int remoteControlPort) {
		this.remoteControlPort = remoteControlPort;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public Timestamp getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Timestamp beginTime) {
		this.beginTime = beginTime;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public long getServiceTime() {
		return serviceTime;
	}
	public void setServiceTime(long serviceTime) {
		this.serviceTime = serviceTime;
	}
	public String getServiceTimeString() {
		return LongToTimeString.getTimeString(serviceTime);
	}
	public void setServiceTimeString(String serviceTimeString) {
		this.serviceTimeString = serviceTimeString;
	}
	
}
