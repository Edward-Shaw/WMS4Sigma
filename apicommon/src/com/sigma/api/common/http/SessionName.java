package com.sigma.api.common.http;

import java.io.Serializable;

public class SessionName implements Serializable{
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    public final static String SN_ORDER_RECHARGE = "_ORDER_RECHARGE";
	public final static String SN_LATEST_TIMESTAMP = "_LATEST_TIMESTAMP";
	
	public final static String SN_CAP_DEVOCE_ID = "_CAP_DEVICE_ID";
	public final static String SN_CAP_PUBLIC_KEY = "_CAP_PUBLIC_KEY";
	
	/**
	 * 使用过的随机字符串(用于接口合法性校验)
	 */
	public final static String SN_HISTORY_RANDOMS = "_HISTORY_RANDOMS";
}
