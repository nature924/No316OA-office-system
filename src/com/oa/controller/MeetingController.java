package com.oa.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.entity.Meeting;
import com.oa.entity.Record;
import com.oa.entity.User;
import com.oa.service.MeetingService;

@RestController
public class MeetingController {
	
	@Autowired
	MeetingService service;

	@RequestMapping("/MeetingList")
    public ModelAndView MeetingList(
			@RequestParam(defaultValue="1") Integer currentPage,HttpServletRequest request,
			Map<String,Object> map){
		User user = (User)request.getSession().getAttribute("user");
		String MeetingName = request.getParameter("key");
		List<Meeting> list = new ArrayList<>();
		Map<String,Object> maps = new HashMap<>();
		maps.put("key", MeetingName);
		maps.put("uid", user!=null?user.getId():null);
		maps.put("state", user!=null?user.getState():null);
		PageHelper.startPage(currentPage,10);
		list=service.selectMeetingList(maps);
		PageInfo<Meeting> pageInfo=new PageInfo<Meeting>(list,8);
		map.put("pageInfo", pageInfo);
		map.put("key", MeetingName);
		return new ModelAndView("view/meeting/list");
	} 
	
	

	@RequestMapping("/toAddMeeting")
    public ModelAndView toAddMeeting(HttpServletRequest request,ModelAndView mv){
		mv.setViewName("view/meeting/add");
		return mv;
	}
	
	@RequestMapping("/addMeeting")
	@ResponseBody
	public boolean addMeeting(HttpServletRequest request , Meeting ss){
		ss.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		User user = (User)request.getSession().getAttribute("user");
		boolean re = false;
		ss.setUid(user.getId());
		ss.setUname(user.getRealName());
		service.addMeeting(ss);
		re = true;
		return re;
	}
	
	@RequestMapping("/deleteMeeting")
    public boolean deleteMeeting(HttpServletRequest request,
			HttpServletResponse response){
		boolean re = false;
		String id = request.getParameter("id");
		service.deleteMeeting(Integer.parseInt(id));
		re = true;
	    return re;
	}
	
	
	@RequestMapping("/toUpdateMeeting")
    public ModelAndView toUpdateMeeting(HttpServletRequest request,ModelAndView mv){
	    String id=request.getParameter("id");
	    Meeting ff = service.selectMeetingById(Integer.parseInt(id));
		mv.addObject("MeetingDate", ff);
		mv.setViewName("view/meeting/update");
		return mv;
	}
	

	@RequestMapping("/updateMeeting")
	@ResponseBody
	public boolean updateMeeting(Meeting ss){
		boolean re = false;
		service.updateMeeting(ss);
		re = true;
		return re;
	}
	
	@RequestMapping("/selectRecordById")
	@ResponseBody
	public List<Record> selectRecordById(Record record){
		List<Record> recordList = service.selectRecordById(record);
		return recordList;
	}
	
	
	@RequestMapping("/applyMeet")
	@ResponseBody
	public boolean applyMeet(HttpServletRequest request,Record record){
		User user = (User)request.getSession().getAttribute("user");
		boolean re = false;
		if(user !=null) {
			record.setUid(user.getId());
			record.setUname(user.getRealName());
			record.setUstate(user.getState());
			record.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
			service.applyMeet(record);
			if(user.getState().equals("3")) {
				String state = record.getState().equals("同意")?"2":"1";
				service.updateMeetingState(state,record.getWid());
			}
			else if(user.getState().equals("4")) {
				String state = record.getState().equals("同意")?"4":"3";
				service.updateMeetingState(state,record.getWid());
			}
			re = true;
		}
		return re;
	}

}
