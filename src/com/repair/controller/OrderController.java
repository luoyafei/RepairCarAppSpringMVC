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
        
        for(RepairOrder ro : listR) {
        	ro.setOrderState(cOrderS(ro.getOrderState()));
        	ro.setOrderType(cOrderT(ro.getOrderType()));
        }
        
        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        jo.addProperty("listR", gson.toJson(listR));
        
        out.print(jo.toString());
        out.flush();
        out.close();
	}
	
	private String cOrderS(String orderState) {
		switch (orderState) {
			case "0":
				return "新单";
			case "1":
				return "上级公司确认";
			case "2":
				return "派单确认";
			case "3":
				return "挂起";
			case "4":
				return "正在处理";
			case "5":
				return "成功处理";
			case "6":
				return "提交结算";
			case "7":
				return "结算完成";
			case "8":
				return "订单撤销";
			default:
				return "订单状态异常";
		}
	}
	private String cOrderT(String orderType) {
		switch (orderType) {
			case "0":
				return "维修单";
			case "1":
				return "保养单";
			case "2":
				return "拆除单";
			case "3":
				return "加装单";
			default:
				return "单据类型异常";
		}
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
        
        order.setOrderState(cOrderS(order.getOrderState()));
        order.setOrderType(cOrderT(order.getOrderType()));
        
        
        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        jo.addProperty("order", gson.toJson(order));
        
        out.print(jo.toString());
        out.flush();
        out.close();
	}
	
	
	@RequestMapping(value="/suspendOrder", method=RequestMethod.POST)
	public void suspendOrder(HttpServletResponse response, @RequestParam(value="orderId") String orderId, @RequestParam(value="suspendReason") String suspendReason) {
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out= null;
        try {
			out = response.getWriter();
		} catch (IOException e) {}
        
        boolean success = true;
        String reason = "";
        
        RepairOrder order = rod.getRepairOrderById(orderId);
        order.setOrderState("3");//将状态设置为挂起
        order.setSuspendReason(suspendReason);
        if(rod.updateRepairOrder(order)) {
        	success = true;
        } else
        	reason = "更新失败";
        
        JsonObject jo = new JsonObject();
        jo.addProperty("success", success);
        jo.addProperty("reason", reason);
        
        out.print(jo.toString());
        out.flush();
        out.close();
	}
	
	@RequestMapping(value="/okOrder2", method=RequestMethod.POST)
	public void okOrder2(HttpServletResponse response, @RequestParam(value="orderId") String orderId) {
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out= null;
        try {
			out = response.getWriter();
		} catch (IOException e) {}
        
        boolean success = true;
        String reason = "";
        
        RepairOrder order = rod.getRepairOrderById(orderId);
        order.setOrderState("2");//将状态设置为挂起
        
        if(rod.updateRepairOrder(order)) {
        	success = true;
        } else
        	reason = "更新失败";
        
        JsonObject jo = new JsonObject();
        jo.addProperty("success", success);
        jo.addProperty("reason", reason);
        
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
