package com.oa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oa.entity.Meeting;
import com.oa.entity.Record;

public interface MeetingDao {

	List<Meeting> selectMeetingList(@Param("map") Map<String, Object> maps);

	void addMeeting(@Param("cc")  Meeting ss);

	void deleteMeeting(@Param("id")  int parseInt);

	Meeting selectMeetingById(@Param("id")  int parseInt);

	void updateMeeting(@Param("cc")  Meeting ss);

	void applyMeet(@Param("cc") Record record);

	void updateMeetingState(@Param("state") String state,@Param("wid") Integer wid);

	List<Record> selectRecordById(@Param("record") Record record);

}
