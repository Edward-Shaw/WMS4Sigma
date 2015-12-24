package com.sigma.api.common.http;

import com.sigma.api.common.utils.RandomStringBuilder;

public class PurchaseOrder extends Order {
	private String app;
	private String product;
	private String user;
	private int state;
	
	public PurchaseOrder(){
		super();
	}
	
	public PurchaseOrder(String app, String product, String user, int expired){
		super(RandomStringBuilder.random(16), expired);
		
		this.app = app;
		this.product = product;
		this.user = user;
	}
	
   public PurchaseOrder(String app, String product, String user, int expired, int state){
        super(RandomStringBuilder.random(16), expired);
        
        this.app = app;
        this.product = product;
        this.user = user;
        this.state = state;
    }
	
	public PurchaseOrder(String app, String product, String user){
		super(RandomStringBuilder.random(16), 30);
		
		this.app = app;
		this.product = product;
		this.user = user;
	}
	
	public PurchaseOrder(int state){
	    this.state = state;
	}
	public String getApp(){
		return app;
	}
	public void setApp(String app){
		this.app = app;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }	
}
