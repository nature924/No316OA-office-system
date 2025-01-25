package com.oa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oa.entity.Leave;
import com.oa.entity.Record;
import com.oa.entity.Statics;

public interface LeaveDao {

	List<Leave> selectLeaveList(@Param("map") Map<String, Object> maps);

	void addLeave(@Param("cc")  Leave ss);

	void deleteLeave(@Param("id")  int parseInt);

	Leave selectLeaveById(@Param("id")  int parseInt);

	void updateLeave(@Param("cc")  Leave ss);

	void applyLeave(@Param("cc") Record record);

	void updateLeaveState(@Param("state") String state,@Param("wid") Integer wid);

	List<Record> selectRecordById(@Param("record") Record record);

	List<Statics> selectLeaveStatics();

}
