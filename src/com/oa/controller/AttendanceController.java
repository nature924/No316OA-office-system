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
import com.oa.entity.Attendance;
import com.oa.entity.User;
import com.oa.service.AttendanceService;
import com.oa.service.UserService;

@RestController
public class AttendanceController {
	
	@Autowired
	AttendanceService service;
	
	@Autowired
	UserService userService;
	

	@RequestMapping("/AttendanceList")
    public ModelAndView AttendanceList(
			@RequestParam(defaultValue="1") Integer currentPage,HttpServletRequest request,
			Map<String,Object> map){
		User user = (User)request.getSession().getAttribute("user");
		String AttendanceName = request.getParameter("key");
		List<Attendance> list = new ArrayList<>();
		Map<String,Object> maps = new HashMap<>();
		maps.put("key", AttendanceName);
		maps.put("uid", user!=null?user.getId():null);
		PageHelper.startPage(currentPage,10);
		list=service.selectAttendanceList(maps);
		PageInfo<Attendance> pageInfo=new PageInfo<Attendance>(list,8);
		map.put("pageInfo", pageInfo);
		map.put("key", AttendanceName);
		return new ModelAndView("view/attendance/list");
	} 
	
	

	@RequestMapping("/toAddAttendance")
    public ModelAndView toAddAttendance(HttpServletRequest request,ModelAndView mv){
		List<User> userList = userService.selectUserList2();
		mv.addObject("userList", userList);
		mv.setViewName("view/attendance/add");
		return mv;
	}
	
	@RequestMapping("/addAttendance")
	@ResponseBody
	public boolean addAttendance(Attendance ss){
		ss.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		boolean re = false;
		User user = userService.selectUserById(ss.getUid());
		ss.setUname(user.getRealName());
		service.addAttendance(ss);
		re = true;
		return re;
	}
	
	@RequestMapping("/deleteAttendance")
    public boolean deleteAttendance(HttpServletRequest request,
			HttpServletResponse response){
		boolean re = false;
		String id = request.getParameter("id");
		service.deleteAttendance(Integer.parseInt(id));
		re = true;
	    return re;
	}
	
	
	@RequestMapping("/toUpdateAttendance")
    public ModelAndView toUpdateAttendance(HttpServletRequest request,ModelAndView mv){
	    String id=request.getParameter("id");
	    Attendance ff = service.selectAttendanceById(Integer.parseInt(id));
	    List<User> userList = userService.selectUserList2();
		mv.addObject("userList", userList);
		mv.addObject("AttendanceDate", ff);
		mv.setViewName("view/attendance/update");
		return mv;
	}
	

	@RequestMapping("/updateAttendance")
	@ResponseBody
	public boolean updateAttendance(Attendance ss){
		boolean re = false;
		User user = userService.selectUserById(ss.getUid());
		ss.setUname(user.getRealName());
		service.updateAttendance(ss);
		re = true;
		return re;
	}

}
