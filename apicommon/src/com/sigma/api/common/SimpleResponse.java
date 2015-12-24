package com.sigma.api.common;

/**
 * TODO: APIResponse从SimpleResponse派生
 * @author XuGang
 *
 */
public class SimpleResponse {
	private int code;
	private String message;
	private Object result;
	
	public SimpleResponse(int code, Object result){
		this.code = code;
		this.result = result;
	}
	
	@Deprecated
	public SimpleResponse(int code, String message, Object result){
		this.code = code;
		this.message = message;
		this.result = result;
	}
	
	public SimpleResponse(int code){
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}
	public void setResult(Object result){
		this.result = result;
	}
}
