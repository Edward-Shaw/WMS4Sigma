package com.sigma.http.converter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;

import com.sigma.api.common.APIResponse;
import com.sigma.api.common.utils.MD5Digest;
import com.sigma.api.common.utils.RandomStringBuilder;

public class JSONHttpMessageConverter extends MappingJacksonHttpMessageConverter {

	@Override
	protected void writeInternal(Object object, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		if(object instanceof APIResponse){
			APIResponse responseObject = (APIResponse) object;
			HttpHeaders headers = outputMessage.getHeaders();
		
			/**
			 * 增加对回复内容的签名需要的字段
			 */
			///设置response的cap-timestamp, cap-random, cap-code, cap-message
			String timestamp = String.valueOf((new Date()).getTime());
			String random = RandomStringBuilder.random(16);
			String code = String.valueOf(responseObject.getCode());
			String message = "";
			String plain = code+message+random+timestamp+responseObject.getAppPrivateKey();
			
/*			headers.set("cap-timestamp", timestamp);
			headers.set("cap-random", random);*/
			try {
				headers.set("cap-digest", (new MD5Digest()).md5crypt(plain));
			} catch (NoSuchAlgorithmException e) {
			}
			
//			super.writeInternal(responseObject.getResult(), outputMessage);
			//根据code为response增加message
			responseObject.setMessage(APIResponse.getMessage(responseObject.getCode()));
			super.writeInternal(responseObject, outputMessage);
		}
	}
}
