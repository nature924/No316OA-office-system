package com.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.dao.UserDao;
import com.oa.entity.Admin;
import com.oa.entity.User;

@Service
public class UserService {
	
	
	@Autowired 
	UserDao dao;

	public Admin selectAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return dao.selectAdmin(admin);
	}

	public User selectUser(User user) {
		// TODO Auto-generated method stub
		return dao.selectUser(user);
	}

	public List<User> selectUserList(Map<String, Object> maps) {
		// TODO Auto-generated method stub
		return dao.selectUserList(maps);
	}

	public void addUser(User ss) {
		// TODO Auto-generated method stub
		dao.addUser(ss);
	}

	public void deleteUser(int parseInt) {
		// TODO Auto-generated method stub
		dao.deleteUser(parseInt);
	}

	public User selectUserById(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectUserById(id);
	}

	public void updateUser(User ss) {
		// TODO Auto-generated method stub
		dao.updateUser(ss);
	}

	public User selectUserByUserName(String username) {
		// TODO Auto-generated method stub
		return dao.selectUserByUserName(username);
	}

	public List<User> selectUserByState(String string) {
		// TODO Auto-generated method stub
		return  dao.selectUserByState(string);
	}
	public List<User> selectUserList2() {
		// TODO Auto-generated method stub
		return  dao.selectUserList2();
	}

	public void updateAdminPassword(String newpass, Integer id) {
		// TODO Auto-generated method stub
		dao.updateAdminPassword(newpass,id);
	}

}
