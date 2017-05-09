package com.repair.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.repair.bean.OrderLog;
import com.repair.bean.RepairOrder;
import com.repair.dao.OrderLogDao;
import com.repair.dao.RepairOrderDao;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private RepairOrderDao rod;
	private OrderLogDao old;
	public RepairOrderDao getRod() {
		return rod;
	}
	@Resource(name="repairDao")
	public void setRod(RepairOrderDao rod) {
		this.rod = rod;
	}
	public OrderLogDao getOld() {
		return old;
	}
	@Resource(name="orderLogDao")
	public void setOld(OrderLogDao old) {
		this.old = old;
	}
	
	@RequestMapping(value="/getOrders", method=RequestMethod.POST)
	public void getOrders(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="userId") String userId) {
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out= null;
        try {
			out = response.getWriter();
		} catch (IOException e) {}

        List<RepairOrder> listR = rod.getRepairOrders(0, 10, userId, "0", "0");
        
        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        jo.addProperty("listR", gson.toJson(listR));
        
        out.print(jo.toString());
        out.flush();
        out.close();
	}
	
	@RequestMapping(value="/getOrder", method=RequestMethod.POST)
	public void getOrder(HttpServletResponse response, @RequestParam(value="orderId") String orderId) {
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out= null;
        try {
			out = response.getWriter();
		} catch (IOException e) {}
        
        RepairOrder order = rod.getRepairOrderById(orderId);
        
        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        jo.addProperty("order", gson.toJson(order));
        
        out.print(jo.toString());
        out.flush();
        out.close();
	}
	
	/**
	 * 上传日志
	 * @param response
	 * @param logContent
	 * @param userId
	 */
	@RequestMapping(value="/uploadLog", method=RequestMethod.POST)
	public void uploadLog(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="logContent") String logContent,  @RequestParam(value="userId") String userId) {
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
        	 if(logContent != null && logContent.trim() != "") {
             	OrderLog log = new OrderLog();
                 log.setCreateDate(new Timestamp(System.currentTimeMillis()));
                 log.setLogContent(logContent);
                 log.setPersonId(userId);
                 
                 if(old.saveFeedBack(log)) {
                	 success = true;
                 } else
                	 reason = "存入失败";
             } else 
             	reason = "日志内容为空";
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
