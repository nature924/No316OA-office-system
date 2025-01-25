package com.oa.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oa.entity.Admin;
import com.oa.entity.User;
import com.oa.service.UserService;

@RestController
public class UserController {
	
	@Autowired 
	UserService us;
	
	
	/**
	 * ��¼
	 * @param username
	 * @param request
	 * @param password
	 * @param session
	 * @param response
	 * @param mv
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/login")
	@ResponseBody
     public boolean login(@RequestParam("username")String username,
    		 HttpServletRequest request,@RequestParam("password")String password,
    		 HttpSession session,HttpServletResponse response,ModelAndView mv) throws ServletException, IOException {	
		session.removeAttribute("admin");
		session.removeAttribute("user");
	    String type=request.getParameter("type").toString();
	    User user =new User();
	    Admin admin = new Admin();
		request.getSession().setAttribute("type", type);
		boolean re = false;
	    if(type != null && type.equals("1")){
	    	admin.setUsername(username);
	    	admin.setPassword(password);
		    Admin admin1 = us.selectAdmin(admin);
		    if(admin1 != null){
				request.getSession().setAttribute("admin", admin1);
				session.setAttribute("admin", admin1);
				re = true;
		    }
	    }else if(type != null && type.equals("2")){
	    	user.setUsername(username);
	    	user.setPassword(password);
	    	User te = us.selectUser(user);
	    	if(te != null){
				request.getSession().setAttribute("user", te);
				session.setAttribute("user", te);
				re = true;
		    }
	    }
		return re;
			
	}
	

	@RequestMapping("/userList")
    public ModelAndView UserList(
			@RequestParam(defaultValue="1") Integer currentPage,HttpServletRequest request,
			Map<String,Object> map){
		User user = (User)request.getSession().getAttribute("user");
		String userName = request.getParameter("key");
		List<User> list = new ArrayList<>();
		Map<String,Object> maps = new HashMap<>();
		maps.put("username", userName);
		maps.put("uid", user!=null?user.getId():null);
		PageHelper.startPage(currentPage,10);
		list=us.selectUserList(maps);
		PageInfo<User> pageInfo=new PageInfo<User>(list,8);
		map.put("pageInfo", pageInfo);
		map.put("key", userName);
		return new ModelAndView("view/user/list");
	} 
	
	

	@RequestMapping("/toAddUser")
    public ModelAndView toAddUser(HttpServletRequest request,ModelAndView mv){
		mv.setViewName("view/user/add");
		return mv;
	}
	
	@RequestMapping("/addUser")
	@ResponseBody
	public boolean addUser(User ss){
		ss.setTimes(new  SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
		boolean re = false;
		User user = us.selectUserByUserName(ss.getUsername());
		if(user == null) {
			us.addUser(ss);
			re = true;
		}
		return re;
	}
	
	@RequestMapping("/deleteUser")
    public boolean deleteUser(HttpServletRequest request,
			HttpServletResponse response){
		boolean re = false;
		String id = request.getParameter("id");
		us.deleteUser(Integer.parseInt(id));
		re = true;
	    return re;
	}
	
	
	@RequestMapping("/toUpdateUser")
    public ModelAndView toUpdateUser(HttpServletRequest request,ModelAndView mv){
	    String id=request.getParameter("id");
	    User ff = us.selectUserById(Integer.parseInt(id));
		mv.addObject("userDate", ff);
		mv.setViewName("view/user/update");
		return mv;
	}
	
	
	@RequestMapping("/findMyInfo")
    public ModelAndView findMyInfo(HttpServletRequest request,ModelAndView mv){
		User user = (User)request.getSession().getAttribute("user");
	    User ff = us.selectUserById(user.getId());
		mv.addObject("userDate", ff);
		mv.setViewName("view/user/update_myinfo");
		return mv;
	}
	
	@RequestMapping("/toUpdateAdminPassword")
    public ModelAndView toUpdateAdminPassword(HttpServletRequest request,ModelAndView mv){
		mv.setViewName("view/admin/updatePassword");
		return mv;
	}
	

	@RequestMapping("/updateAdminPassword")
	@ResponseBody
	public boolean updateAdminPassword(HttpServletRequest request){
		boolean re = false;
		Admin admin = (Admin)request.getSession().getAttribute("admin");
		String nowpass = request.getParameter("nowpass");
		String newpass = request.getParameter("newpass");
		if(admin !=null && admin.getPassword().equals(nowpass)) {
			us.updateAdminPassword(newpass,admin.getId());
			re = true;
		}
		return re;
	}
	
	@RequestMapping("/updateUser")
	@ResponseBody
	public boolean updateUser(User ss){
		boolean re = false;
		us.updateUser(ss);
		re = true;
		return re;
	}

}
