package com.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.dao.SalaryDao;
import com.oa.entity.Salary;

@Service
public class SalaryService {
	
	@Autowired 
	SalaryDao dao;

	public List<Salary> selectSalaryList(Map<String, Object> maps) {
		// TODO Auto-generated method stub
		return dao.selectSalaryList(maps);
	}

	public void addSalary(Salary ss) {
		// TODO Auto-generated method stub
		dao.addSalary(ss);
	}

	public void deleteSalary(int parseInt) {
		// TODO Auto-generated method stub
		dao.deleteSalary(parseInt);
	}

	public Salary selectSalaryById(int parseInt) {
		// TODO Auto-generated method stub
		return dao.selectSalaryById(parseInt);
	}

	public void updateSalary(Salary ss) {
		// TODO Auto-generated method stub
		dao.updateSalary(ss);
	}

	public void updateSalaryType(Salary ss) {
		// TODO Auto-generated method stub
		dao.updateSalaryType(ss);
	}

	public Salary selectSalaryByDates(String worktime, Integer uid) {
		// TODO Auto-generated method stub
		return dao.selectSalaryByDates(worktime,uid);
	}

}
