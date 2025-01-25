package com.oa.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oa.dao.SuperviseDao;
import com.oa.entity.Statics;
import com.oa.entity.Supervise;

@Service
public class SuperviseService {
	
	@Autowired 
	SuperviseDao dao;

	public List<Supervise> selectSuperviseList(Map<String, Object> maps) {
		// TODO Auto-generated method stub
		return dao.selectSuperviseList(maps);
	}

	public void addSupervise(Supervise ss) {
		// TODO Auto-generated method stub
		dao.addSupervise(ss);
	}

	public void deleteSupervise(int parseInt) {
		// TODO Auto-generated method stub
		dao.deleteSupervise(parseInt);
	}

	public Supervise selectSuperviseById(int parseInt) {
		// TODO Auto-generated method stub
		return dao.selectSuperviseById(parseInt);
	}

	public void updateSupervise(Supervise ss) {
		// TODO Auto-generated method stub
		dao.updateSupervise(ss);
	}

	public List<Statics> selectSuperviseStatics() {
		// TODO Auto-generated method stub
		return dao.selectSuperviseStatics();
	}

}
