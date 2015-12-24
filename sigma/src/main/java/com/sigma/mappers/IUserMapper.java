package com.sigma.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository("userMapper")
public interface IUserMapper {

	/**
	 * 获取所有用户的总数
	 * @return
	 */
	@Select("SELECT COUNT(*) FROM `user`")
	int getCount();

	/**
	 * 获取所有用户列表
	 * @param fnum
	 * @param size
	 * @return
	 */
	@Select("SELECT u.ID AS id, u.`NAME` AS `name`, u.DEPARTMENT AS department, u.ROLE AS role FROM `user` AS u WHERE ROLE = 'EDITOR'")
	List<Map<String, Object>> getUserList(int fnum, int size);

	@Select("SELECT * from `user` where NAME = #{0}")
	Map<String, Object> selectUserByName(String userName);
	
	@Update("UPDATE user SET PASSWORD = #{1} WHERE ID = #{0}")
	int updateUserPassword(String username, String newpassword);

	@Update("UPDATE user SET NAME = #{1}, ROLE = #{2}, DEPARTMENT = #{3} WHERE ID = #{0}")
	int modify(String id, String name, String role, String department);
	
	@Insert("INSERT user (NAME, ROLE, DEPARTMENT, PASSWORD) VALUES (#{0}, #{1}, #{2}, #{3})")
	int create(String name, String role, String department,
			String password);
	
	@Delete("DELETE from USER where USER.ID = #{0}")
	int delete(String id);

	@Select("SELECT u.ID AS id, u.`NAME` AS `name`, u.DEPARTMENT AS department, u.ROLE AS role FROM `user` AS u")
	List<Map<String, Object>> getAllUserList(int from, int size);
}
