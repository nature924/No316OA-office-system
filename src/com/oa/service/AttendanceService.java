package com.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.dao.AttendanceDao;
import com.oa.entity.Attendance;

@Service
public class AttendanceService {
	
	@Autowired 
	AttendanceDao dao;

	public List<Attendance> selectAttendanceList(Map<String, Object> maps) {
		// TODO Auto-generated method stub
		return dao.selectAttendanceList(maps);
	}

	public void addAttendance(Attendance ss) {
		// TODO Auto-generated method stub
		dao.addAttendance(ss);
	}

	public void deleteAttendance(int parseInt) {
		// TODO Auto-generated method stub
		dao.deleteAttendance(parseInt);
	}

	public Attendance selectAttendanceById(int parseInt) {
		// TODO Auto-generated method stub
		return dao.selectAttendanceById(parseInt);
	}

	public void updateAttendance(Attendance ss) {
		// TODO Auto-generated method stub
		dao.updateAttendance(ss);
	}

}
