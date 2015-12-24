package com.sigma.common;

import java.sql.Timestamp;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class TimestampJsonValueProcessor implements JsonValueProcessor {

	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return null;
	}

	@Override
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		return ((Timestamp)arg1).toString();
	}
}
