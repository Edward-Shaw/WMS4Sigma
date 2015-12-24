package com.sigma.common.json;

import java.sql.Timestamp;

import net.sf.ezmorph.object.AbstractObjectMorpher;

public class TimestampMorpher extends AbstractObjectMorpher {

	private static TimestampMorpher instance = null;
	public static TimestampMorpher getMorpher(){
		if(instance == null){
			instance = new TimestampMorpher();
		}
		return instance;
	}
	
	/**
	 * @getMorpher 是唯一获取TimestampMorpher实例的方法
	 */
	TimestampMorpher(){}
	
	@Override
	public Object morph(Object arg0) {
		return new Timestamp((Long) arg0);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class morphsTo() {
		return Timestamp.class;
	}
}