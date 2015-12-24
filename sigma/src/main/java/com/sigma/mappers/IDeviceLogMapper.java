package com.sigma.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

@Repository("deviceLogMapper")
public interface IDeviceLogMapper {

	@SelectProvider(type = SqlProvider.class, method = "getDeviceLogList")
	public List<Map<String, ?>> selectDeviceLogList(@Param("type") String type, @Param("page") int page, @Param("size") int size);
}
