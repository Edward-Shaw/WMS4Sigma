package com.sigma.common.models;


/**
 * APPM/SP/SM的基类
 * @author XuGang
 *
 */
public class CAPComponent {
	private int id = 0;
	
	private String name = "";
	
	///运行状态
	private String state2 = ResourceState.OFFLINE.name();
	
	//设置状态
	private String state = ResourceState.NORMAL.name();
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
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
	
	public void setId(int id) {
		this.id = id;
	}
}
