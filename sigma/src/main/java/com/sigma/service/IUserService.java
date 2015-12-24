package com.sigma.service;

import java.util.List;
import java.util.Map;

public interface IUserService {
	/**
	 * 获得所有的user
	 * @param from 记录开始索引
	 * @param size 返回最大记录数
	 * @return 如果存在有效用户列表, 0索引处为paging信息 {"count": <total-count>}
	 */
	List<Map<String, Object>> getUserList(int from, int size);
	
	Map<String, Object> getUserByName(String userName);

	int changePassword(String encodeStr, String newpassword);

	int modify(String id, String name, String role, String department);

	int create(String name, String role, String department,
			String password);

	int delete(String id);

	List<Map<String, Object>> getAllUserList(int i, int size);
}
