package com.oa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oa.entity.Admin;
import com.oa.entity.User;

public interface UserDao {

	Admin selectAdmin(@Param("admin") Admin admin);

	User selectUser(@Param("user") User user);

	List<User> selectUserList(@Param("map") Map<String, Object> maps);

	void addUser(@Param("cc")  User ss);

	void deleteUser(@Param("id") int parseInt);

	User selectUserById(@Param("id")  Integer id);

	void updateUser(@Param("cc") User ss);

	User selectUserByUserName(@Param("username") String username);

	List<User> selectUserByState(@Param("state") String string);

	List<User> selectUserList2();

	void updateAdminPassword(@Param("newpass") String newpass,@Param("id") Integer id);

}
