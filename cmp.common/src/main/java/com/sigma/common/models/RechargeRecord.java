package com.sigma.common.models;

import java.sql.Timestamp;

public class RechargeRecord {
	private int id = 0;
	private int userId = 0;
	private int amount = 0;
	private Timestamp time = new java.sql.Timestamp(System.currentTimeMillis());
	private int result = -99;
	private String message = "unknown message";
	private String gameId = "";
	private String cpId = "";
	
	public RechargeRecord(){
		
	}
	public RechargeRecord(int userId, int amount, Timestamp time, int result, String message){
		this.userId = userId;
		this.amount = amount;
		this.time = time;
		this.result = result;
		this.message = message;
	}
	
	public RechargeRecord(int userId, int amount, Timestamp time, int result, String message, String gameId, String cpId){
		this.userId = userId;
		this.amount = amount;
		this.time = time;
		this.result = result;
		this.message = message;
		this.gameId = gameId;
		this.cpId = cpId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getGameId() {
		return gameId;
	}
	public void setGameId(String gameId) {
		this.gameId = gameId;
	}
	public String getCpId() {
		return cpId;
	}
	public void setCpId(String cpId) {
		this.cpId = cpId;
	}
}
