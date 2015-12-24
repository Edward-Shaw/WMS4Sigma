package com.sigma.mappers;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository("rentLogMapper")
public interface IRentLogMapper {

	@SelectProvider(type = SqlProvider.class, method = "getRentLogList")
	public List<Map<String, ?>> selectRentLogList(@Param("user") String user, @Param("page") int page, @Param("size") int size);

	@Insert("INSERT INTO rent_log (COMPANY, RENTER, PURPOSE, RENT_TYPE, PHONE, RENT_TIME, EXCEPT_TIME) VALUES(#{0}, #{1}, #{2}, #{3}, #{4}, #{5}, #{6});")
	public int insertRentLog(String company, String renter, String purpose, String rent_type, String phone,
			Timestamp rent_time, Timestamp except_time);

	@SelectProvider(type = SqlProvider.class, method = "selectCurrentRentLogList")
	public List<Map<String, ?>> selectCurrentRentLogList(@Param("user") String user, @Param("page") int page, @Param("size") int size);
}
