package com.oa.controller;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oa.entity.Attendance;
import com.oa.entity.Supervise;
import com.oa.service.AttendanceService;
import com.oa.service.SuperviseService;


/*@CrossOrigin*/
@Controller

public class UtilController {
	
	@Autowired
	AttendanceService service;
	
	@Autowired
	SuperviseService superviseService;
	
	@RequestMapping("toRegister")
	public String toRegsiteAct(HttpServletRequest request) {
		return "view/register";
	}
	
	@RequestMapping("toEcharts")
	public String toEcharts(HttpServletRequest request) {
		return "view/echarts";
	}
	
	
	@RequestMapping("toLogin")
	public String toLogin(HttpSession session) {
		session.removeAttribute("admin");
		session.removeAttribute("user");
		return "view/login";
	}
	
	@RequestMapping("UserPageAct")
	public String UserPageAct() {
		return "view/userList";
	}
	
	
	@RequestMapping("toLoginOutAct")
	public String toLoginOutAct(HttpSession session) {
		session.removeAttribute("admin");
		session.removeAttribute("user");
		return "view/login";
	}
	@RequestMapping("toMain")
	public String toMain(HttpServletRequest request,HttpSession session) {
		Map<String,Object> map = new HashMap<String,Object>();
		List<Attendance> attendaceList = service.selectAttendanceList(map);
		request.setAttribute("attendaceList", attendaceList);
		List<Supervise> superviseList = superviseService.selectSuperviseList(map);
		request.setAttribute("superviseList", superviseList);
		return "view/main";
	}
	@RequestMapping("toConsoleAct")
	public String toWelocmeAct() {	
		return "view/console";
	}
	
    @ResponseBody
    @RequestMapping("/upload")
    public Map upload(MultipartFile file,HttpServletRequest request){

        String prefix="";
        String dateStr="";
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                String uuid = UUID.randomUUID()+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                String filepath = "F:\\workspace\\flower_ssm\\WebContent\\img\\" + dateStr+"\\"+uuid+"." + prefix;
                
                File files=new File(filepath);
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src","/img/"+ dateStr+"/"+uuid+"." + prefix);
                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (Exception e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;

    }
    

    @ResponseBody
    @RequestMapping("/upload2")
    public Map upload2(MultipartFile file,HttpServletRequest request){

        String prefix="";
        String dateStr="";
        OutputStream out = null;
        InputStream fileInput=null;
        try{
            if(file!=null){
                String originalName = file.getOriginalFilename();
                prefix=originalName.substring(originalName.lastIndexOf(".")+1);
                Date date = new Date();
                String uuid = UUID.randomUUID()+"";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateStr = simpleDateFormat.format(date);
                String filepath = "D:\\upload\\" + dateStr+"\\"+uuid+"." + prefix;
                
                File files=new File(filepath);
                System.out.println(filepath);
                if(!files.getParentFile().exists()){
                    files.getParentFile().mkdirs();
                }
                file.transferTo(files);
                Map<String,Object> map2=new HashMap<>();
                Map<String,Object> map=new HashMap<>();
                map.put("code",0);
                map.put("msg","");
                map.put("data",map2);
                map2.put("src",dateStr+"/"+uuid+"." + prefix);
                return map;
            }

        }catch (Exception e){
        }finally{
            try {
                if(out!=null){
                    out.close();
                }
                if(fileInput!=null){
                    fileInput.close();
                }
            } catch (Exception e) {
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("code",1);
        map.put("msg","");
        return map;

    }
    

}
