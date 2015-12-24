package com.sigma.service;

import java.util.List;
import java.util.Map;

public interface IDeviceLogService {
	
	List<Map<String, ?>> getDeviceLogList(String type, int page, int size);
}
