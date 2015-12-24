package com.sigma.oapi.web.sdk;

import net.sf.json.JSON;

import org.apache.commons.httpclient.HttpMethod;

import com.sigma.api.common.ApiRequest;

public class SecuredApiRequest extends ApiRequest {

	public SecuredApiRequest(String call, ApiRequestMethod method) {
		super(call, method);
	}

	@Override
	protected String getBaseURL() {
		return null;
	}

	@Override
	public JSON execute() {
		
/*		
		try {
			Throwable ex = new Throwable();
			StackTraceElement[] stackTraceElements = ex.getStackTrace();
			String apiId = "";
			String encryptToken = "";
			if(stackTraceElements != null){
				for(int i = 0; i < 2; i ++){
					apiId = stackTraceElements[i].getClassName();
					if(apiId.contains("gepg")){
						apiId = Configuration.getInstance().get("authentication.GEPG.apiId");
						encryptToken = SecretUtils.DataEncrypt(Configuration.getInstance().get("authentication.GEPG.token"), Configuration.getInstance().get("authentication.GEPG.secret"));
						break;
					} else if (apiId.contains("cmp.web.manager")) {
						apiId = Configuration.getInstance().get("authentication.CMPWEBMS.apiId");
						encryptToken = SecretUtils.DataEncrypt(Configuration.getInstance().get("authentication.CMPWEBMS.token"), Configuration.getInstance().get("authentication.CMPWEBMS.secret"));
						break;
					}
				}
			}
			m.setRequestHeader("http.authentication-token", encryptToken);
			m.setRequestHeader("http.authentication-apiID", apiId);
			m.setRequestHeader("http.authentication-consumerId", "1");
			
			int state = this.getHttpClient().executeMethod(m);
			//String resp = m.getResponseBodyAsString();
			//System.out.print(resp);
			if(state == 200){
//				String body2 = m.getResponseBodyAsString();
//				Header header = m.getResponseHeader("http.authentication-token");
//				System.out.println(header.toString());
				InputStream bs = m.getResponseBodyAsStream();
				BufferedReader br = new BufferedReader( new InputStreamReader(bs, "utf8") );
				String tempBody;
				StringBuffer bufferBody = new StringBuffer();
				while((tempBody = br.readLine()) != null){
					bufferBody.append(tempBody);
				}
				String body = bufferBody.toString();
				if(body.length() > 0){
					obj = JSONObject.fromObject(body);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
*/
		
		return super.execute();
	}

	@Override
	protected void beforeExecute(HttpMethod m) {
	}
}
