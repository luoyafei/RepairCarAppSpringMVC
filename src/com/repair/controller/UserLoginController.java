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
import com.repair.bean.User;
import com.repair.dao.UserDao;

@Controller
@RequestMapping("/user")
public class UserLoginController {

	private UserDao ud;
	@Resource(name="userDao")
	private void setAd(UserDao ud) {
		this.ud = ud;
	}
	
	@RequestMapping(value="/userLogin", method=RequestMethod.POST)
	public void userLogin(HttpServletRequest request, HttpServletResponse response,@RequestParam(value="type") String type, @RequestParam(value="name") String name, @RequestParam(value="password") String password) {
		
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

		if(type != null && name != null && name.trim().hashCode() != 0 && password != null && password.trim().hashCode() != 0) {
			User u = ud.getUserByName(name, type);
			if(u != null) {
				if(u.getUserPwd().equals(password)) {
					success = true;
					//request.getSession().setAttribute("user", u);
					jo.addProperty("userId", u.getUserId());
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
		User user = (User) request.getSession().getAttribute("user");
		if(user != null) {
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
