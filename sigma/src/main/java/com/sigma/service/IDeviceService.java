package com.sigma.service;

import java.util.List;
import java.util.Map;

public interface IDeviceService {

	/**
	 * 根据参数获取指定道具列表
	 * @author Edward
	 * @param status
	 * @param serialcode 
	 * @param page
	 * @param size
	 * @return
	 */
	List<Map<String, ?>> getDeviceList(String status, String user, String serialcode, int page, int size);

	int addDevice(String model, String asset_code, String serial_num1, String serial_num12, String kakou1,
			String kakou2, String name, Integer count, String quality, String manager);

	List<Map<String, ?>> getDeviceListByUser(String user, String status,
			String serialcode, int page, int size);

	/**
	 * 根据@id获取设备信息
	 * @param id
	 * @return
	 */
	Map<String, Object> getDeviceById(String id);

	int modifyDevice(String id, String model, String asset_code, String serial_num1,
			String serial_num2, String kakou1, String kakou2, String name,
			Integer count, String quality);

	int distribute(String id, String manager);

	int rentDevice(String id, String renter, String company, String position,
			String phone, String purpose, String rent_type, String rent_time,
			String expect_time);

	int instore(String id, String actualTime, String status);

	int delete(String id, String decrease);
}
