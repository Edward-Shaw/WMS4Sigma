package com.sigma.oapi.web.sdk;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSON;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.NameValuePair;

import com.sigma.api.common.ApiRequest;
import com.sigma.api.common.ApiRequest.ApiRequestMethod;
import com.sigma.api.common.utils.MD5Digest;

public class SecuresafeApiRequest extends ApiRequest{
	
	public SecuresafeApiRequest(String call, ApiRequestMethod method) {
		super(call, method);
	}

	private String privateKey;
	
	public void setPrivateKey(String key){
		this.privateKey = key;
	}
	public String getPrivateKey(){
		return this.privateKey;
	}

	@Override
	public JSON execute() {
		///对参数按照key进行排列
		List<NameValuePair> params = this.getParameters();
		Collections.sort(params, new Comparator<NameValuePair>(){
			@Override
			public int compare(NameValuePair o1, NameValuePair o2) {
				NameValuePair param0 = (NameValuePair) o1, param1 = (NameValuePair) o2;
				return param0.getName().compareTo(param1.getName());
			}
		});
		
		///将值全部串接之后+privateKey, 然后md5获得摘要
		Iterator<NameValuePair> it = params.iterator();
		String plain = "";
		while(it.hasNext()){
			plain += it.next().getValue();
		}
		plain += this.getPrivateKey();
		
		///将摘要作为一个参数进行传递
		String digest = "";
		try {
			digest = (new MD5Digest()).md5crypt(plain);
		} catch (NoSuchAlgorithmException e) {
		}
		this.addParameter(new NameValuePair("digest", digest));
		
		return super.execute();
	}

	@Override
	protected String getBaseURL() {
		return null;
	}
	@Override
	protected void beforeExecute(HttpMethod m) {
		// TODO Auto-generated method stub
		
	}

}
