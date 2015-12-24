package com.sigma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sigma.mappers.IDeviceMapper;
import com.sigma.mappers.IUserMapper;
import com.sigma.service.IDeviceService;

@Service("deviceService")
public class DeviceService implements IDeviceService {	
	
	@Autowired
	@Qualifier("deviceMapper")
	private IDeviceMapper deviceMapper = null;
	
	@Override
	public List<Map<String, ?>> getDeviceList(String status, String user, String serialcode, int page, int size) {
		if(deviceMapper == null){
			return null;
		}
		
		List<Map<String, ?>> result = deviceMapper.selectDevices(status, user, serialcode, page * size, size);
		if(result != null && result.size() > 0){
			int count = deviceMapper.countDevices(status, user);
			Map<String, Object> paging = new HashMap<>();
			paging.put("count", count);
			result.add(0, paging);
		}
		
		return result;
	}

	@Override
	public int addDevice(String model, String asset_code, String serial_num1, String serial_num12,
			String kakou1, String kakou2, String name, Integer count, String quality,
			String manager) {
		if(deviceMapper == null){
			return 0;
		}
		
		return deviceMapper.insertDevice(model, asset_code, serial_num1, serial_num12,
				kakou1, kakou2, name, count, quality,
				manager);
	}

	@Override
	public List<Map<String, ?>> getDeviceListByUser(String user,
			String status, String serialcode, int page, int size) {
		if(deviceMapper == null){
			return null;
		}
		
		return deviceMapper.selectDevicesByUser(user, status, serialcode, page, size);
	}

	@Override
	public Map<String, Object> getDeviceById(String id) {
		if(deviceMapper == null){
			return null;
		}
		
		return deviceMapper.selectDeviceById(id);
	}

	@Override
	public int modifyDevice(String id, String model, String asset_code,
			String serial_num1, String serial_num2, String kakou1,
			String kakou2, String name, Integer count, String quality) {
		if(deviceMapper == null){
			return 0;
		}
		
		return deviceMapper.updateDevice(id, model, asset_code, serial_num1, serial_num2,
					kakou1, kakou2, name, count, quality);
	}

	@Override
	public int distribute(String id, String manager) {
		if(deviceMapper == null){
			return 0;
		}
		
		return deviceMapper.distribute(id, manager);
	}

	@Override
	public int rentDevice(String id, String renter, String company,
			String position, String phone, String purpose, String rent_type,
			String rent_time, String expect_time) {
		if(deviceMapper == null){
			return 0;
		}
		
		if(rent_time == null || rent_time.isEmpty() || expect_time.isEmpty()){
			return deviceMapper.rentDeviceWithoutTime(id, renter, company, position, phone, purpose, rent_type);
		}
		
		return deviceMapper.rentDevice(id, renter, company, position, phone, purpose, rent_type, rent_time, expect_time);
	}

	@Override
	public int instore(String id, String actualTime, String status) {
		if(deviceMapper == null){
			return 0;
		}
		
		if(status.equalsIgnoreCase("TEMP_OWN")){
			return deviceMapper.deleteTempOwnDevice(id);
		}else if(status.equalsIgnoreCase("TEMP_OWN_RELET")){
			return deviceMapper.updateDeviceRentStatus(id, actualTime, "TEMP_OWN");
		}else{
			return deviceMapper.updateDeviceRentStatus(id, actualTime, "IN_STORE");
		}
	}

	@Override
	@Transactional
	public int delete(String id, String decrease) {
		if(deviceMapper == null){
			return 0;
		}
		
		int res = deviceMapper.updateDeviceStatus(id, decrease);
		if(res != 1){
			return -1;
		}		
		
		return deviceMapper.deleteDevice(id);
	}
			
}
