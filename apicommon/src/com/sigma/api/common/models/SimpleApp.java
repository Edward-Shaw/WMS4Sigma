package com.sigma.api.common.models;


public class SimpleApp extends PublicResource {
	
	private int id = 0;
	private String name = "";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 用于校验对APP资源的访问
	 */
	private String privateKey = "";
	
	
	public int getId() {
		return id;
	}
	public void setId(int productId) {
		this.id = productId;
	}

	public String getPrivateKey() {
        return privateKey;
    }
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }
}
