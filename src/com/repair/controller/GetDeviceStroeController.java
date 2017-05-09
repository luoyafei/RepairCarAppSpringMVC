package com.repair.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.repair.bean.DeviceStroe;
import com.repair.dao.DeviceStroeDao;

@Controller
@RequestMapping("/deviceStroe")
public class GetDeviceStroeController {

	private DeviceStroeDao dsd;
	public DeviceStroeDao getDsd() {
		return dsd;
	}
	@Resource(name="deviceStroeDao")
	public void setDsd(DeviceStroeDao dsd) {
		this.dsd = dsd;
	}
	
	@RequestMapping(value="/getDevices", method=RequestMethod.POST)
	public void getOrders(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out= null;
        try {
			out = response.getWriter();
		} catch (IOException e) {}

        List<DeviceStroe> dss = dsd.getAllDevices();
        
        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        jo.addProperty("dss", gson.toJson(dss));
        
        out.print(jo.toString());
        out.flush();
        out.close();
	}
}
