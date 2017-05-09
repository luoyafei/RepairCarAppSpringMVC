package com.repair.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.JsonObject;
import com.repair.bean.FeedBack;
import com.repair.dao.FeedBackDao;

@Controller
@RequestMapping("/feedBack")
public class FeedBackController {

	private FeedBackDao fbd;
	public FeedBackDao getFbd() {
		return fbd;
	}
	@Resource(name="feedBackDao")
	public void setFbd(FeedBackDao fbd) {
		this.fbd = fbd;
	}

	/**
	 * 上传意见反馈
	 * @param request
	 * @param response
	 * @param userId
	 * @param feedContent
	 */
	@RequestMapping(value="/submitFeedBack", method=RequestMethod.POST)
	public void uploadLog(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="feedContent") String feedContent, @RequestParam(value="userId") String userId) {
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out= null;
        boolean success = false;
        String reason = "";
        try {
			out = response.getWriter();
		} catch (IOException e) {}
        
        if(userId != null) {
        	 if(feedContent != null && feedContent.trim() != "") {
        		 
        		 FeedBack fb = new FeedBack();
        		 fb.setFeedContent(feedContent);
        		 fb.setBackTime(new Timestamp(System.currentTimeMillis()));
        		 fb.setFeedBackId(userId);
                 
                 if(fbd.saveFeedBack(fb)) {
                	 success = true;
                 } else
                	 reason = "存入失败";
             } else 
             	reason = "意见内容为空";
        } else
        	reason = "请先进行登陆操作";
        
        JsonObject jo = new JsonObject();
        jo.addProperty("success", success);
        jo.addProperty("reason", reason);
        
        out.print(jo.toString());
        out.flush();
        out.close();
	}
}
