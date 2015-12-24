package com.sigma.service.impl;

import java.util.HashMap;
import java.util.Map;

public class BaseService {
	
	/**
	 * 格式化开始时间和结束时间
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	protected Map<String, String> formateTime(String beginTime, String endTime){
		Map<String, String> result = new HashMap<String, String>();
		if(beginTime.equals("") && endTime.equals("")){
			
		} else if(beginTime.equals("")){
			beginTime = "2013";
		} else if(endTime.equals("")){
			endTime = "2050";
		}
		result.put("begin", beginTime);
		result.put("end", endTime);
		
		return result;
		
	}

}
