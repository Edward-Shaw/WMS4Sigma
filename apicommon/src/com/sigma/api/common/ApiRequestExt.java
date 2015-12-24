package com.sigma.api.common;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;

import com.sigma.api.common.ApiRequest;
import com.sigma.util.Configuration;

public class ApiRequestExt extends ApiRequest {
	
	private List<NameValuePair> headers = new ArrayList<NameValuePair>();
	
	public void addHeaders(String key, String value){
		NameValuePair header = new NameValuePair(key, value);
		headers.add(header);
	}
	
	public ApiRequestExt(String call, ApiRequestMethod method) {
		super(call, method);
	}

	@Override
	protected String getBaseURL() {
		/**
		 * FIXME: Configure所读取的配置,应该是具体应用层面的,不是lib层面的
		 */
		String oapiIp = Configuration.getInstance().get("oapi.baseurl");
		return oapiIp;
	}

	@Override
	protected void beforeExecute(HttpMethod m) {
		for(NameValuePair nv : headers){
			m.setRequestHeader(nv.getName(), nv.getValue());
		}
	}

}
