package com.repair.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonObject;
import com.repair.bean.Admin;
import com.repair.dao.AdminDao;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

	private AdminDao ad;
	@Resource(name="adminDao")
	private void setAd(AdminDao ad) {
		this.ad = ad;
	}
	
	@RequestMapping(value="/adminLogin", method=RequestMethod.POST)
	public void adminLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="name") String name, @RequestParam(value="password") String password) {
		
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
		JsonObject jo = new JsonObject();
		boolean success = false;
		String reason = "";
		try {
			out = response.getWriter();
		} catch(IOException e) {}

		if(name != null && name.trim().hashCode() != 0 && password != null && password.trim().hashCode() != 0) {
			Admin a = ad.getAdminByName(name);
			if(a != null) {
				if(a.getAdminPwd().equals(password)) {
					success = true;
					request.getSession().setAttribute("admin", a);
				} else
					reason = "密码错误";
			} else
				reason = "该用户不存在";
		} else
			reason = "请将信息填写完整";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		out.flush();
		out.close();
	}
	
	@RequestMapping(value="/checkLogin", method=RequestMethod.GET)
	public void checkLogin(HttpServletRequest request, HttpServletResponse response) {
		
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
		JsonObject jo = new JsonObject();
		boolean success = false;
		String reason = "";
		try {
			out = response.getWriter();
		} catch(IOException e) {}
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if(admin != null) {
				success = true;
		} else
			reason = "未登陆";
		
		jo.addProperty("success", success);
		jo.addProperty("reason", reason);
		
		out.print(jo.toString());
		out.flush();
		out.close();
	}
}
