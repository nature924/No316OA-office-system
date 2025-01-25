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
import com.oa.entity.Leave;
import com.oa.entity.Record;
import com.oa.entity.Statics;
import com.oa.entity.User;
import com.oa.service.LeaveService;

@RestController
public class LeaveController {
	
	@Autowired
	LeaveService service;

	@RequestMapping("/LeaveList")
    public ModelAndView LeaveList(
			@RequestParam(defaultValue="1") Integer currentPage,HttpServletRequest request,
			Map<String,Object> map){
		User user = (User)request.getSession().getAttribute("user");
		String LeaveName = request.getParameter("key");
		List<Leave> list = new ArrayList<>();
		Map<String,Object> maps = new HashMap<>();
		maps.put("key", LeaveName);
		maps.put("uid", user!=null?user.getId():null);
		maps.put("state", user!=null?user.getState():null);
		PageHelper.startPage(currentPage,10);
		list=service.selectLeaveList(maps);
		PageInfo<Leave> pageInfo=new PageInfo<Leave>(list,8);
		map.put("pageInfo", pageInfo);
		map.put("key", LeaveName);
		return new ModelAndView("view/leave/list");
	} 
	
	@RequestMapping("/LeaveMineList")
    public ModelAndView LeaveMineList(
			@RequestParam(defaultValue="1") Integer currentPage,HttpServletRequest request,
			Map<String,Object> map){
		User user = (User)request.getSession().getAttribute("user");
		String LeaveName = request.getParameter("key");
		List<Leave> list = new ArrayList<>();
		Map<String,Object> maps = new HashMap<>();
		maps.put("key", LeaveName);
		maps.put("uid", user!=null?user.getId():null);
		maps.put("mine", "1");
		PageHelper.startPage(currentPage,10);
		list=service.selectLeaveList(maps);
		PageInfo<Leave> pageInfo=new PageInfo<Leave>(list,8);
		map.put("pageInfo", pageInfo);
		map.put("key", LeaveName);
		return new ModelAndView("view/leave/mineList");
	} 
	
	

	@RequestMapping("/toAddLeave")
    public ModelAndView toAddLeave(HttpServletRequest request,ModelAndView mv){
		mv.setViewName("view/leave/add");
		return mv;
	}
	
	@RequestMapping("/addLeave")
	@ResponseBody
	public boolean addLeave(HttpServletRequest request , Leave ss){
		ss.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		User user = (User)request.getSession().getAttribute("user");
		boolean re = false;
		if(user !=null) {
			ss.setUid(user.getId());
			ss.setUname(user.getRealName());
			ss.setUstate(user.getState());
			if(user.getState().equals("1")) {
				ss.setState("0");
			}else if(user.getState().equals("2")) {
				ss.setState("2");
			}else if(user.getState().equals("3")) {
				ss.setState("4");
			}
			service.addLeave(ss);
			re = true;
		}
		
		return re;
	}
	
	@RequestMapping("/deleteLeave")
    public boolean deleteLeave(HttpServletRequest request,
			HttpServletResponse response){
		boolean re = false;
		String id = request.getParameter("id");
		service.deleteLeave(Integer.parseInt(id));
		re = true;
	    return re;
	}
	
	
	@RequestMapping("/toUpdateLeave")
    public ModelAndView toUpdateLeave(HttpServletRequest request,ModelAndView mv){
	    String id=request.getParameter("id");
	    Leave ff = service.selectLeaveById(Integer.parseInt(id));
		mv.addObject("LeaveDate", ff);
		mv.setViewName("view/leave/update");
		return mv;
	}
	

	@RequestMapping("/updateLeave")
	@ResponseBody
	public boolean updateLeave(Leave ss){
		boolean re = false;
		service.updateLeave(ss);
		re = true;
		return re;
	}
	
	@RequestMapping("/selectLeaveRecordById")
	@ResponseBody
	public List<Record> selectRecordById(Record record){
		List<Record> recordList = service.selectRecordById(record);
		return recordList;
	}
	
	
	@RequestMapping("/applyLeave")
	@ResponseBody
	public boolean applyLeave(HttpServletRequest request,Record record){
		User user = (User)request.getSession().getAttribute("user");
		boolean re = false;
		if(user !=null) {
			record.setUid(user.getId());
			record.setUname(user.getRealName());
			record.setUstate(user.getState());
			record.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
			service.applyLeave(record);
			if(user.getState().equals("3")) {
				String state = record.getState().equals("同意")?"4":"3";
				service.updateLeaveState(state,record.getWid());
			}
			else if(user.getState().equals("4")) {
				String state = record.getState().equals("同意")?"6":"5";
				service.updateLeaveState(state,record.getWid());
			}else if(user.getState().equals("2")) {
				String state = record.getState().equals("同意")?"2":"1";
				service.updateLeaveState(state,record.getWid());
			}
			re = true;
		}
		return re;
	}
	
	@RequestMapping("/selectLeaveStatics")
	@ResponseBody
	public List<Statics> selectLeaveStatics(){
		List<Statics> staticsList = service.selectLeaveStatics();
		return staticsList;
	}

}
