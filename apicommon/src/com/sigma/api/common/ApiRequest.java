package com.sigma.api.common;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public abstract class ApiRequest{
	
	public enum ApiRequestMethod {
		GET, POST, PUT, DELETE
	}
	
	protected HttpClient getHttpClient(){
		HttpClient client = new HttpClient();
		client.getParams().setContentCharset("utf-8");
		return client;
	}
	
	protected String call;
	protected ApiRequestMethod method;
	protected List<NameValuePair> params;
	
	protected List<NameValuePair> getParameters(){
		return this.params;
	}
	
	protected abstract String getBaseURL();
	
	protected abstract void beforeExecute(HttpMethod m);
	
	/***
	 * Construct a OAPI request
	 * @param call: api path
	 * @param method: request method
	 */
	public ApiRequest(String call, ApiRequestMethod method){
		this.setCall(call);
		this.setMethod(method);
		
		this.params = new ArrayList<NameValuePair>();
		params.add(new NameValuePair("abc", "567"));
	}
	
	public ApiRequest(String call, ApiRequestMethod method, List<NameValuePair> params){
		this.setCall(call);
		this.setMethod(method);
		
		this.params = params;
	}
	
	public void addParameter(String key, String value){
		this.params.add(new NameValuePair(key, value));
	}
	
	public void addParameter(NameValuePair param){
		this.params.add(param);
	}
	
	public JSON execute(){
		
		HttpMethod m = null;
		
		String fullCall = this.getBaseURL() + this.call;
		
		NameValuePair[] array = new NameValuePair[params.size()];
		int idx = 0;
		for(NameValuePair nv : params){
			array[idx++] = nv;
		}
		
		if(this.method == ApiRequestMethod.GET){
			m = new GetMethod(fullCall);
			((GetMethod) m).setQueryString(array);
			
		}else if(this.method == ApiRequestMethod.POST){
			m = new PostMethod(fullCall);
			((PostMethod) m).setRequestBody(array);
			
		} else if (this.method == ApiRequestMethod.PUT) {
			m = new PutMethod(fullCall);
			
			//TODO: now support StringPart only. Later we should support FilePart!
			/*ArrayList<Part> list = new ArrayList<Part>();
			for(int i = 0; i < pa.length; i++){
				NameValuePair pair = pa[i];
				list.add(new StringPart(pair.getName(), pair.getValue()));
			}
			Part[] parts = list.toArray(new Part[list.size()]);
			MultipartRequestEntity e = new MultipartRequestEntity(parts, m.getParams());
			((PutMethod) m).setRequestEntity(e);
			*/
			
			String content = "";
			for(int i = 0; i < params.size(); i++){
				NameValuePair pair = params.get(i);
				content += (pair.getName() + "=" + pair.getValue());
				if(i + 1 < params.size())
					content += "&";
			}
			StringRequestEntity e = null;
			
			try {
				e = new StringRequestEntity(content, "application/x-www-form-urlencoded", "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			((PutMethod) m).setRequestEntity(e);
			
		} else if(this.method == ApiRequestMethod.DELETE){
			m = new DeleteMethod(fullCall);
			((DeleteMethod) m).setQueryString((NameValuePair[]) params.toArray());
		}
		
		JSON obj = JSONObject.fromObject(null);
		
		this.beforeExecute(m);
		
		try {
			int state = this.getHttpClient().executeMethod(m);
			if(state == HttpStatus.SC_OK){
				InputStream bs = m.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader( new InputStreamReader(bs, "utf8") );
				String temp = null;
				StringBuffer buffer = new StringBuffer();
				while((temp = br.readLine()) != null){
					buffer.append(temp);
				}
				String body = buffer.toString();
				if(body.length() > 0){
					
					obj = JSONSerializer.toJSON(body);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(m != null){
			m.releaseConnection();
		}
		
		return obj;
	}
	
	public void setCall(String call){
		this.call = call;
	}
	
	public void setMethod(ApiRequestMethod method) {
		this.method = method;
	}
}
