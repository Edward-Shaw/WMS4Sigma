package com.sigma.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sigma.mappers.IDeviceLogMapper;
import com.sigma.service.IDeviceLogService;

@Service("deviceLogService")
public class DeviceLogService implements IDeviceLogService {	
	
	@Autowired
	@Qualifier("deviceLogMapper")
	private IDeviceLogMapper deviceLogMapper = null;
	
	@Override
	public List<Map<String, ?>> getDeviceLogList(String type, int page, int size) {
		if(deviceLogMapper == null){
			return null;
		}
		
		return deviceLogMapper.selectDeviceLogList(type, page, size);
	}
}
