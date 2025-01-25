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
import com.oa.entity.Statics;
import com.oa.entity.Supervise;
import com.oa.entity.User;
import com.oa.service.SuperviseService;
import com.oa.service.UserService;

@RestController
public class SuperviseController {
	
	@Autowired
	SuperviseService service;
	
	@Autowired
	UserService userService;
	

	@RequestMapping("/SuperviseList")
    public ModelAndView SuperviseList(
			@RequestParam(defaultValue="1") Integer currentPage,HttpServletRequest request,
			Map<String,Object> map){
		User user = (User)request.getSession().getAttribute("user");
		String SuperviseName = request.getParameter("key");
		List<Supervise> list = new ArrayList<>();
		Map<String,Object> maps = new HashMap<>();
		maps.put("key", SuperviseName);
		maps.put("uid", user!=null?user.getId():null);
		PageHelper.startPage(currentPage,10);
		list=service.selectSuperviseList(maps);
		PageInfo<Supervise> pageInfo=new PageInfo<Supervise>(list,8);
		map.put("pageInfo", pageInfo);
		map.put("key", SuperviseName);
		return new ModelAndView("view/supervise/list");
	} 
	
	

	@RequestMapping("/toAddSupervise")
    public ModelAndView toAddSupervise(HttpServletRequest request,ModelAndView mv){
		List<User> userList = userService.selectUserList2();
		mv.addObject("userList", userList);
		mv.setViewName("view/supervise/add");
		return mv;
	}
	
	@RequestMapping("/addSupervise")
	@ResponseBody
	public boolean addSupervise(Supervise ss){
		ss.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		boolean re = false;
		User user = userService.selectUserById(ss.getUid());
		ss.setUname(user.getRealName());
		service.addSupervise(ss);
		re = true;
		return re;
	}
	
	@RequestMapping("/selectSuperviseStatics")
	@ResponseBody
	public List<Statics> selectSuperviseStatics(){
		List<Statics> superviseStatics = service.selectSuperviseStatics();
		return superviseStatics;
	}
	
	@RequestMapping("/deleteSupervise")
    public boolean deleteSupervise(HttpServletRequest request,
			HttpServletResponse response){
		boolean re = false;
		String id = request.getParameter("id");
		service.deleteSupervise(Integer.parseInt(id));
		re = true;
	    return re;
	}
	
	
	@RequestMapping("/toUpdateSupervise")
    public ModelAndView toUpdateSupervise(HttpServletRequest request,ModelAndView mv){
	    String id=request.getParameter("id");
	    Supervise ff = service.selectSuperviseById(Integer.parseInt(id));
	    List<User> userList = userService.selectUserList2();
		mv.addObject("userList", userList);
		mv.addObject("SuperviseDate", ff);
		mv.setViewName("view/supervise/update");
		return mv;
	}
	

	@RequestMapping("/updateSupervise")
	@ResponseBody
	public boolean updateSupervise(Supervise ss){
		boolean re = false;
		User user = userService.selectUserById(ss.getUid());
		ss.setUname(user.getRealName());
		service.updateSupervise(ss);
		re = true;
		return re;
	}

}
