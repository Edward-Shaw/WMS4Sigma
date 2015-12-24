package com.sigma.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sigma.api.common.APIResponse;

public class BaseController {
	
	public final Logger logger = LoggerFactory.getLogger(getClass());
	public StackTraceElement ste = new Throwable().getStackTrace()[0];

	/**
	 * 拦截所有的异常，并将异常信息返回值客户端
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Object exp(HttpServletRequest request, Exception ex) {
		logger.error(ex.getMessage());
		Map<String, Object> j = new HashMap<String, Object>();
		j.put("exception", ex.getMessage());

		return new APIResponse(APIResponse.RC_BAD_REQUEST, j, "x");
	}
}
