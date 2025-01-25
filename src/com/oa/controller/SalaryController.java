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
import com.oa.entity.Salary;
import com.oa.entity.User;
import com.oa.service.SalaryService;
import com.oa.service.UserService;

@RestController
public class SalaryController {
	
	@Autowired
	SalaryService service;
	
	@Autowired
	UserService userService;
	

	@RequestMapping("/SalaryList")
    public ModelAndView SalaryList(
			@RequestParam(defaultValue="1") Integer currentPage,HttpServletRequest request,
			Map<String,Object> map){
		User user = (User)request.getSession().getAttribute("user");
		String SalaryName = request.getParameter("key");
		List<Salary> list = new ArrayList<>();
		Map<String,Object> maps = new HashMap<>();
		maps.put("key", SalaryName);
		maps.put("uid", user!=null?user.getId():null);
		maps.put("ustate", user!=null?user.getState():null);
		PageHelper.startPage(currentPage,10);
		list=service.selectSalaryList(maps);
		PageInfo<Salary> pageInfo=new PageInfo<Salary>(list,8);
		map.put("pageInfo", pageInfo);
		map.put("key", SalaryName);
		return new ModelAndView("view/salary/list");
	} 
	
	

	@RequestMapping("/toAddSalary")
    public ModelAndView toAddSalary(HttpServletRequest request,ModelAndView mv){
		List<User> userList = userService.selectUserList2();
		mv.addObject("userList", userList);
		mv.setViewName("view/salary/add");
		return mv;
	}
	
	@RequestMapping("/addSalary")
	@ResponseBody
	public boolean addSalary(Salary ss){
		ss.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		boolean re = false;
		Salary salary = service.selectSalaryByDates(ss.getWorktime(),ss.getUid());
		if(salary == null) {
			User user = userService.selectUserById(ss.getUid());
			ss.setUname(user.getRealName());
			ss.setTotalmoney(ss.getSqmoeny()-ss.getJsmoney()-ss.getWxmoney()
					-ss.getSbmoney()-ss.getGjjmoney()+ss.getBtmoney()+ss.getGjmoeny()
					+ss.getJbmoney());
			
			service.addSalary(ss);
			re = true;
		}
		return re;
	}
	
	@RequestMapping("/deleteSalary")
    public boolean deleteSalary(HttpServletRequest request,
			HttpServletResponse response){
		boolean re = false;
		String id = request.getParameter("id");
		service.deleteSalary(Integer.parseInt(id));
		re = true;
	    return re;
	}
	
	
	@RequestMapping("/toUpdateSalary")
    public ModelAndView toUpdateSalary(HttpServletRequest request,ModelAndView mv){
	    String id=request.getParameter("id");
	    Salary ff = service.selectSalaryById(Integer.parseInt(id));
	    List<User> userList = userService.selectUserList2();
		mv.addObject("userList", userList);
		mv.addObject("SalaryDate", ff);
		mv.setViewName("view/salary/update");
		return mv;
	}
	

	@RequestMapping("/updateSalary")
	@ResponseBody
	public boolean updateSalary(Salary ss){
		boolean re = false;
		User user = userService.selectUserById(ss.getUid());
		ss.setUname(user.getRealName());
		ss.setUname(user.getRealName());
		ss.setTotalmoney(ss.getSqmoeny()-ss.getJsmoney()-ss.getWxmoney()
				-ss.getSbmoney()-ss.getGjjmoney()+ss.getBtmoney()+ss.getGjmoeny()
				+ss.getJbmoney());
		service.updateSalary(ss);
		re = true;
		return re;
	}
	
	
	@RequestMapping("/updateSalaryType")
	@ResponseBody
	public boolean updateSalaryType(Salary ss){
		boolean re = false;
		service.updateSalaryType(ss);
		re = true;
		return re;
	}

}
