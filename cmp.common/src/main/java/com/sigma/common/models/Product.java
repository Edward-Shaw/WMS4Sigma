package com.sigma.common.models;

import java.sql.Timestamp;

public class Product {
	
	private String id = "";
	
	private String productType = "APP";
	
	private String purchaseType = "-1";
	
	private double price = .0;
	
	private String state = ResourceState.NORMAL.name();
	
	private String icon = "";
	
	private String desc = "";
	
	private String name = "";
	
	private Timestamp createTime;
	
	private String suggested = "FALSE";
	
	private int sales = 0;
	
	private int runNumber = 0;
	
	public Product(String id){
	}
	
	public Product() {
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public void setState(ResourceState state) {
		this.state = state.name();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	
	public String getSuggested() {
		return suggested;
	}

	public void setSuggested(String suggested) {
		this.suggested = suggested;
	}
	
	public int getSales() {
		return sales;
	}
	
	public void setSales(int sales) {
		this.sales = sales;
	}
	
	public int getRunNumber() {
		return runNumber;
	}
	
	public void setRunNumber(int runNumber) {
		this.runNumber = runNumber;
	}
}

