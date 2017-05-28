package com.repair.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.JsonObject;
import com.repair.bean.ToOnline;
import com.repair.bean.User;
import com.repair.dao.OnlineDao;
import com.repair.dao.UserDao;
import com.repair.tools.FileUploadUtil;

@Controller
@RequestMapping("/file")
public class FileUploadController {

	private UserDao ud;
	private OnlineDao od;
	
	public UserDao getUd() {
		return ud;
	}
	@Resource(name="userDao")
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	public OnlineDao getOd() {
		return od;
	}
	@Resource(name="onlineDao")
	public void setOd(OnlineDao od) {
		this.od = od;
	}
	
	@RequestMapping(value="/uploadImg", method=RequestMethod.POST)  
    public void upload(@RequestParam("studentPhoto") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {  
System.out.println(file.getSize());
		
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = null;
        try {
			out = response.getWriter();
		} catch (IOException e) {}
        
        String filePath = FileUploadUtil.uploadFile(file, request);
        out.print(filePath);
/*		if(userId != null && content != null) {
			User user = ud.getUserById(userId);
			if(user != null) {
				String filePath = FileUploadUtil.uploadFile(file, request);
				
				if(filePath != null && filePath != "") {
					ToOnline to = new ToOnline();
					to.setCarPicture(filePath);
					to.setCreateDate(new Timestamp(System.currentTimeMillis()));
					to.setOnlineContent(content);
					//to.setRemark(remark);
					to.setUserId(userId);
					if(od.saveOnline(to))
						success = true;
					else
						reason = "存入失败";
					
				} else 
					reason = "上传失败";
				
			} else
				reason = "清闲进行登陆";
		} else
			reason = "请填写信息";*/
		
	        
        out.flush();
        out.close();
    }
	
	
	@RequestMapping(value="/onlineRepair", method=RequestMethod.POST)  
    public void onlineRepair(HttpServletRequest request, HttpServletResponse response, @RequestParam("userId") String userId, @RequestParam("content") String content, @RequestParam("filePath") String filePath) throws IOException {  
		
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = null;
        try {
			out = response.getWriter();
		} catch (IOException e) {}
        JsonObject jo = new JsonObject();
        boolean success = false;
        String reason = "";
        
		if(userId != null && content != null) {
			User user = ud.getUserById(userId);
			if(user != null) {
				
				if(filePath != null && filePath != "") {
					ToOnline to = new ToOnline();
					to.setCarPicture(filePath);
					to.setCreateDate(new Timestamp(System.currentTimeMillis()));
					to.setOnlineContent(content);
					//to.setRemark(remark);
					to.setUserId(userId);
					if(od.saveOnline(to))
						success = true;
					else
						reason = "存入失败";
					
				} else 
					reason = "上传失败";
				
			} else
				reason = "清闲进行登陆";
		} else
			reason = "请填写信息";
	        
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		out.flush();
		out.close();
    }
	
	 @RequestMapping(value="/download", method=RequestMethod.POST)  
	    public void download(String fileName, HttpServletResponse response) throws IOException {  
	        OutputStream os = response.getOutputStream();  
	        try {  
	            response.reset();  
	            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);  
	            response.setContentType("image/jpeg; charset=utf-8");  
	            os.write(FileUtils.readFileToByteArray(FileUploadUtil.getFile(fileName)));  
	            os.flush();  
	        } finally {  
	            if (os != null) {  
	                os.close();  
	            }  
	        }  
	    } 
}
