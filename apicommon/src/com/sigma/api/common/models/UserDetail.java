package com.sigma.api.common.models;

/**
 * @deprecated
 */
public class UserDetail extends User{

	private int points = 0;
	private int balance = 0;
	private int voucher = 0;
	
	public UserDetail(){
	}
	
	public UserDetail(String key, String name, int points){
		super(key, name);
		this.points = points;
	}
	
	public UserDetail(String key, String name, int points, int balance, int voucher){
		super(key, name);
		this.points = points;
		this.balance = balance;
		this.voucher = voucher;
	}
	
	/**
	 * 积分
	 * @return
	 */
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	//获取余额
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getVoucher() {
		return voucher;
	}

	public void setVoucher(int voucher) {
		this.voucher = voucher;
	}
	

}
