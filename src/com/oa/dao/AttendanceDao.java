package com.oa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oa.entity.Attendance;

public interface AttendanceDao {

	List<Attendance> selectAttendanceList(@Param("map") Map<String, Object> maps);

	void addAttendance(@Param("cc")  Attendance ss);

	void deleteAttendance(@Param("id")  int parseInt);

	Attendance selectAttendanceById(@Param("id")  int parseInt);

	void updateAttendance(@Param("cc")  Attendance ss);

}
