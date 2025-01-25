package com.oa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oa.entity.Statics;
import com.oa.entity.Supervise;

public interface SuperviseDao {

	List<Supervise> selectSuperviseList(@Param("map") Map<String, Object> maps);

	void addSupervise(@Param("cc")  Supervise ss);

	void deleteSupervise(@Param("id")  int parseInt);

	Supervise selectSuperviseById(@Param("id")  int parseInt);

	void updateSupervise(@Param("cc")  Supervise ss);

	List<Statics> selectSuperviseStatics();

}
