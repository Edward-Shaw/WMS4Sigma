package com.sigma.common.models;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.sigma.common.LongToTimeString;

public class AppHistory {
	
	private int id = 0;
	private App app = null;
	private User user = null;
	private String appM = null;
	private String clientAddress = "";
	private int clientPort = 0;
	private int serverPort = 0;
	private int clientTypeID = 0;
	private String remoteControlAddress = "";
	private int remoteControlPort = 0;
	private String resolution = " * ";
	private Timestamp beginTime;
	private Timestamp endTime;
	private String exception = "";
	private String state = ResourceState.NORMAL.name();
	private String beginTimeString = "";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getClientTypeID() {
		return clientTypeID;
	}
	public void setClientTypeID(int clientTypeID) {
		this.clientTypeID = clientTypeID;
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
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public String getException() {
		return exception;
	}
	public void setException(String exception) {
		this.exception = exception;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBeginTimeString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(beginTime);
	}
	public void setBeginTimeString(String beginTimeString) {
		this.beginTimeString = beginTimeString;
	}
}
