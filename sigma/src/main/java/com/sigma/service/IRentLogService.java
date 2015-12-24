package com.sigma.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface IRentLogService {

	List<Map<String, ?>> getRentLogList(String user, int page, int size);

	int addRentLog(String company, String renter, String purpose, String rent_type, String phone,
			Timestamp rent_time, Timestamp except_time);

	List<Map<String, ?>> getCurrentRentLogList(String user, int page, int size);
}
