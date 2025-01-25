package com.oa.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.oa.entity.Salary;

public interface SalaryDao {

	List<Salary> selectSalaryList(@Param("map") Map<String, Object> maps);

	void addSalary(@Param("cc")  Salary ss);

	void deleteSalary(@Param("id")  int parseInt);

	Salary selectSalaryById(@Param("id")  int parseInt);

	void updateSalary(@Param("cc")  Salary ss);

	void updateSalaryType(@Param("cc") Salary ss);

	Salary selectSalaryByDates(@Param("worktime") String worktime,@Param("uid")  Integer uid);

}
