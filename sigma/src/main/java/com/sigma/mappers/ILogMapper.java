package com.sigma.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sigma.api.common.models.Log;

@Repository("logMapper")
public interface ILogMapper {	
		
	@Select("SELECT ID FROM user WHERE USER_KEY = #{0}")
	int getClientID(String userKey);
		
	@Insert("INSERT INTO running_log (USER_ID, APP_ID,  CONTENT, TIME) VALUE (#{userId}, #{appId},  #{content}, #{time})")
	void insertLog(Log log);

	@Select("SELECT PRODUCT_ID FROM app WHERE APP_KEY= #{0}")
	int getAppID(String appKey);

	/**
	 * 在log表中插入一条记录
	 * @param type：INFO | ERROR | DEBUG
	 * @param line
	 * @param function
	 * @return
	 */
	@Insert("INSERT INTO log(TYPE, LINE, FUNCTION) VALUE (#{0}, #{1}, #{2})")
	int insert(String type, int line, String function);

	/**
	 * 获取表log中的所有数据
	 * @return
	 */
	/*
	@Select("SELECT t.ID AS ID, t.TYPE AS TYPE, t.LINE AS LINE, t.`FUNCTION` AS `FUNCTION`, "
			+ "date_format(t.CREATE_TIME, '%Y/%m/%d %H:%i:%s') AS TIME FROM log AS t") */
	@Select("SELECT * from log")
	List<Map<String, Object>> getLogs();
	}

