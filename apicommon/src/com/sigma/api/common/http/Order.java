package com.sigma.api.common.http;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单. 用来唯一标识一次充值或者一次购买
 * @author XuGang
 *
 */
public class Order implements Serializable{
    
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String key;				//订单唯一标识
	private Date createTime;	//订单创建时间
	private int expired;			//超时秒数
	private String status;         //状态
	
	public Order(){}
	
	public Order(String key){
		this.key = key;
		this.expired = 30;
		this.createTime = new Date();
	}
	public Order(String key, int expired){
		this.key = key;
		this.expired = expired;
		this.createTime = new Date();
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public int getExpired() {
		return expired;
	}
	public void setExpired(int expired) {
		this.expired = expired;
	}
	
	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean checkExpired(){
		return (new Date()).getTime() - this.createTime.getTime() > this.expired;
	}
}
