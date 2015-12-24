package com.sigma.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sigma.mappers.IRentLogMapper;
import com.sigma.service.IRentLogService;

@Service("rentLogService")
public class RentLogService implements IRentLogService {	
	
	@Autowired
	@Qualifier("rentLogMapper")
	private IRentLogMapper rentLogMapper = null;

	@Override
	public List<Map<String, ?>> getRentLogList(String user, int page, int size) {
		if(rentLogMapper == null){
			return null;
		}
		
		return rentLogMapper.selectRentLogList(user, page, size);
	}

	@Override
	public int addRentLog(String company, String renter, String purpose, String rent_type, String phone,
			Timestamp rent_time, Timestamp except_time) {
		if(rentLogMapper == null){
			return -1;
		}
		
		if(rentLogMapper.insertRentLog(company, renter, purpose, rent_type, phone, rent_time, except_time) != 1){
			return -2;
		}
		
		return 0;
	}

	@Override
	public List<Map<String, ?>> getCurrentRentLogList(String user, int page,
			int size) {
		if(rentLogMapper == null){
			return null;
		}
		
		return rentLogMapper.selectCurrentRentLogList(user, page, size);
	}
			
}
