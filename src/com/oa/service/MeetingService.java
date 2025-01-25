package com.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.dao.MeetingDao;
import com.oa.entity.Meeting;
import com.oa.entity.Record;

@Service
public class MeetingService {
	
	@Autowired 
	MeetingDao dao;

	public List<Meeting> selectMeetingList(Map<String, Object> maps) {
		// TODO Auto-generated method stub
		return dao.selectMeetingList(maps);
	}

	public void addMeeting(Meeting ss) {
		// TODO Auto-generated method stub
		dao.addMeeting(ss);
	}

	public void deleteMeeting(int parseInt) {
		// TODO Auto-generated method stub
		dao.deleteMeeting(parseInt);
	}

	public Meeting selectMeetingById(int parseInt) {
		// TODO Auto-generated method stub
		return dao.selectMeetingById(parseInt);
	}

	public void updateMeeting(Meeting ss) {
		// TODO Auto-generated method stub
		dao.updateMeeting(ss);
	}

	public void applyMeet(Record record) {
		// TODO Auto-generated method stub
		dao.applyMeet(record);
	}

	public void updateMeetingState(String state, Integer wid) {
		// TODO Auto-generated method stub
		dao.updateMeetingState(state,wid);
	}

	public List<Record> selectRecordById(Record record) {
		// TODO Auto-generated method stub
		return dao.selectRecordById(record);
	}

}
