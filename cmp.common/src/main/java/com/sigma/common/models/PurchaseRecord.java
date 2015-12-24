package com.sigma.common.models;

import java.sql.Timestamp;

public class PurchaseRecord{
	private String name;
	private int price;
	private String date;	
	public PurchaseRecord(){
		
	}
	public PurchaseRecord(String name, int price, String date){
		this.name = name;
		this.price = price;
		this.date = date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
