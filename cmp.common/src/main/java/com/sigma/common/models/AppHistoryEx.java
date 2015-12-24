package com.sigma.common.models;

import java.sql.Timestamp;

public class AppHistoryEx {	
	private App app = null;
	private long countrun = 0;
	private long timecount = 0;
	private long longestruntime = 0;
	private Timestamp lastruntime;
	
	public App getApp() {
		return app;
	}
	public void setApp(App app) {
		this.app = app;
	}
	public long getCountrun() {
		return countrun;
	}
	public void setCountrun(long countrun) {
		this.countrun = countrun;
	}
	public long getTimecount() {
		return timecount;
	}
	public void setTimecount(long timecount) {
		this.timecount = timecount;
	}
	public long getLongestruntime() {
		return longestruntime;
	}
	public void setLongestruntime(long longestruntime) {
		this.longestruntime = longestruntime;
	}
	public Timestamp getLastruntime() {
		return lastruntime;
	}
	public void setLastruntime(Timestamp lastruntime) {
		this.lastruntime = lastruntime;
	}
}

