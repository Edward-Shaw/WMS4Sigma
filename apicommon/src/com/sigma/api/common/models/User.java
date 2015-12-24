package com.sigma.api.common.models;


//对应数据库user表

/**
 * @deprecated
 */
public class User extends SimpleUser {
	
	private String createTime = "";
	private String loginTime = "";
	private int blocked = 0;
	
	public User(){}
	
	public User(int id, String name){
		super(id, name);
	}
	
	public User(String key, String name){
		super(key, name);
	}
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	/**
	 * @return 用户是否被禁用
	 */
	public boolean getBlocked() {
		return blocked == 1;
	}

	public void setBlocked(boolean blocked) {
		this.blocked = blocked ? 1 : 0;
	}
}
