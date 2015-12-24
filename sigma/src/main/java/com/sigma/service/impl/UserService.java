package com.sigma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sigma.mappers.IUserMapper;
import com.sigma.service.IUserService;

@Service("userService")
public class UserService implements IUserService{
	@Autowired @Qualifier("userMapper") private IUserMapper userMapper = null;

	@Override
	public List<Map<String, Object>> getUserList(int from, int size) {
		if(userMapper != null){
			return userMapper.getUserList(from, size);
		}
		
		return null;
	}

	@Override
	public Map<String, Object> getUserByName(String userName) {
		if(userMapper != null){
			return userMapper.selectUserByName(userName);
		}
		
		return null;
	}

	@Override
	public int changePassword(String username, String newpassword) {
		if(userMapper != null){
			return userMapper.updateUserPassword(username, newpassword);
		}
		
		return -1;
	}

	@Override
	public int modify(String id, String name, String role, String department) {
		if(userMapper != null){
			return userMapper.modify(id, name, role, department);
		}
		
		return -1;
	}

	@Override
	public int create(String name, String role, String department,
			String password) {
		if(userMapper != null){
			return userMapper.create(name, role, department, password);
		}
		
		return -1;
	}

	@Override
	public int delete(String id) {
		if(userMapper != null){
			return userMapper.delete(id);
		}
		
		return -1;
	}

	@Override
	public List<Map<String, Object>> getAllUserList(int from, int size) {
		if(userMapper != null){
			return userMapper.getAllUserList(from, size);
		}
		
		return null;
	}
}
