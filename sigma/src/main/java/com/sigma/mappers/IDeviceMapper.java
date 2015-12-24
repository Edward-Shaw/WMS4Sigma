package com.sigma.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.springframework.stereotype.Repository;

@Repository("deviceMapper")
public interface IDeviceMapper {

	@SelectProvider(type = SqlProvider.class, method = "countDevices")
	public int countDevices(@Param("status") String status, @Param("user") String user);
	
	@SelectProvider(type = SqlProvider.class, method = "getDeviceList")
	public List<Map<String, ?>> selectDevices(@Param("status") String status, @Param("user") String user, @Param("serialcode") String serialcode, @Param("page") int page, @Param("size") int size);

	@Insert("INSERT INTO device (MODEL, ASSET_CODE, SERIAL_NUMBER_1, SERIAL_NUMBER_2, BAYONET_1, BAYONET_2, NAME, COUNT, QUALITY_GRADE, MANAGER) VALUES(#{0}, #{1}, #{2}, #{3}, #{4}, #{5}, #{6}, #{7}, #{8}, #{9});")
	public int insertDevice(String model, String asset_code, String serial_num1, String serial_num12,
			String kakou1, String kakou2, String name, Integer count, String quality,
			String manager);
	
	@SelectProvider(type = SqlProvider.class, method = "selectDeviceListByUser")
	public List<Map<String, ?>> selectDevicesByUser(@Param("user") String user,
			@Param("status") String status, @Param("serialcode") String serialcode, @Param("page") int page, @Param("size") int size);

	@Select({
		"SELECT * FROM device",
		"WHERE ID = #{0}"
		})
	public Map<String, Object> selectDeviceById(String id);

	@Update("update device set MODEL = #{1}, ASSET_CODE = #{2}, SERIAL_NUMBER_1 = #{3}, SERIAL_NUMBER_2 = #{4}, BAYONET_1 = #{5}, BAYONET_2 = #{6}, NAME = #{7}, COUNT = #{8}, QUALITY_GRADE = #{9} WHERE ID = #{0}")
	public int updateDevice(String id, String model, String asset_code,
			String serial_num1, String serial_num2, String kakou1,
			String kakou2, String name, Integer count, String quality);
	
	@Update("update device set MANAGER = #{1} WHERE ID = #{0}")
	public int distribute(String id, String manager);
	
	@Update("update device set STATUS = #{1} WHERE ID = #{0}")
	public int updateDeviceStatus(String id, String status);

	@Insert("INSERT INTO rent (DEVICE_ID, RENTER, COMPANY, POSITION, PHONE, PURPOSE, RENT_TYPE, RENT_TIME, EXPECT_TIME) VALUES(#{0}, #{1}, #{2}, #{3}, #{4}, #{5}, #{6}, #{7}, #{8});")
	public int rentDevice(String id, String renter, String company,
			String position, String phone, String purpose, String rent_type,
			String rent_time, String expect_time);

	@Insert("INSERT INTO rent (DEVICE_ID, RENTER, COMPANY, POSITION, PHONE, PURPOSE, RENT_TYPE) VALUES(#{0}, #{1}, #{2}, #{3}, #{4}, #{5}, #{6});")
	public int rentDeviceWithoutTime(String id, String renter, String company,
			String position, String phone, String purpose, String rent_type);
	
	@UpdateProvider(type = SqlProvider.class, method = "updateDeviceRentStatus")
	public int updateDeviceRentStatus(@Param("id") String id, @Param("actualTime") String actualTime, @Param("status") String status);
	
	@Delete("DELETE FROM device WHERE device.ID = #{0}")
	public int deleteTempOwnDevice(String id);
	
	@Update("DELETE FROM device WHERE device.ID = #{0}")
	public int deleteDevice(String id);

}
