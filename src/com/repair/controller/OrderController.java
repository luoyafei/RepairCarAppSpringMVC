package com.repair.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/order")
public class OrderController {

	@RequestMapping(value="/getOrders", method=RequestMethod.POST)
	public void getOrders(HttpServletResponse response) {
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out= null;
		Vector<Object> allOrders = new Vector<>();//通过后台获取
		allOrders.add("订单0");
		allOrders.add("订单1");
		allOrders.add("订单2");
		allOrders.add("订单3");
		allOrders.add("订单4");
		allOrders.add("订单5");
		allOrders.add("订单6");
		allOrders.add("订单7");
		allOrders.add("订单8");
		Integer maxCount = 100;//通过后台获取所有的订单数
		boolean success = false;//操作是否成功的标志
		String reason = "";//失败的原因
		
        try {
			out = response.getWriter();
			success = true;
		} catch (IOException e) {}
        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        jo.addProperty("success", success);
        jo.addProperty("reason", reason);
        jo.addProperty("maxCount", maxCount);
        jo.add("allOrders", gson.toJsonTree(allOrders));
        out.print(jo.toString());
        out.flush();
        out.close();
	}
}
