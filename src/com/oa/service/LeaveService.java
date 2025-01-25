package com.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.dao.LeaveDao;
import com.oa.entity.Leave;
import com.oa.entity.Record;
import com.oa.entity.Statics;

@Service
public class LeaveService {
	
	@Autowired 
	LeaveDao dao;

	public List<Leave> selectLeaveList(Map<String, Object> maps) {
		// TODO Auto-generated method stub
		return dao.selectLeaveList(maps);
	}

	public void addLeave(Leave ss) {
		// TODO Auto-generated method stub
		dao.addLeave(ss);
	}

	public void deleteLeave(int parseInt) {
		// TODO Auto-generated method stub
		dao.deleteLeave(parseInt);
	}

	public Leave selectLeaveById(int parseInt) {
		// TODO Auto-generated method stub
		return dao.selectLeaveById(parseInt);
	}

	public void updateLeave(Leave ss) {
		// TODO Auto-generated method stub
		dao.updateLeave(ss);
	}

	public void applyLeave(Record record) {
		// TODO Auto-generated method stub
		dao.applyLeave(record);
	}

	public void updateLeaveState(String state, Integer wid) {
		// TODO Auto-generated method stub
		dao.updateLeaveState(state,wid);
	}

	public List<Record> selectRecordById(Record record) {
		// TODO Auto-generated method stub
		return dao.selectRecordById(record);
	}

	public List<Statics> selectLeaveStatics() {
		// TODO Auto-generated method stub
		return dao.selectLeaveStatics();
	}

}
