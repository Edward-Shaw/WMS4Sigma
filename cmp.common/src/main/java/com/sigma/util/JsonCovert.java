package com.sigma.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import com.sigma.common.ResultPaging;
import com.sigma.common.json.TimestampMorpher;

@Deprecated
public class JsonCovert {
	public JsonCovert(){
		
	}

	/**
	 * 将返回的JSON格式ResultPaging转换为ResultPaging对象
	 * @param jobj
	 * @param beanClass
	 * @return
	 */
	@Deprecated
	public static ResultPaging convertJSONArrayToBeans(JSONObject jobj, Class beanClass){
		ResultPaging rp = null;
		if(jobj != null && !jobj.isNullObject()){
			Map<String, Class> cmap = new HashMap<String, Class>();
			cmap.put("list", beanClass);
			///JSONUtils.getMorpherRegistry().registerMorpher(new TimestampMorpher());
			rp = (ResultPaging) JSONObject.toBean(jobj, ResultPaging.class, cmap);
		}
		return rp;
	}
	@Deprecated
	public static Object convertJSONObjectToBean(JSONObject jobj, Class beanClass){
		Object object = new Object();
		if(jobj != null){
			////JSONUtils.getMorpherRegistry().registerMorpher(	new TimestampMorpher());
			object = JSONObject.toBean(jobj, beanClass);
		}
		return object;
	}
}
