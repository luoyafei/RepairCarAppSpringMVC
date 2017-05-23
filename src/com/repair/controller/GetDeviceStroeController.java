package com.repair.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.repair.bean.DeviceStroe;
import com.repair.bean.HasDevice;
import com.repair.bean.ReceiveLog;
import com.repair.dao.DeviceStroeDao;
import com.repair.dao.HasDeviceDao;
import com.repair.dao.ReceiveLogDao;

@Controller
@RequestMapping("/deviceStroe")
public class GetDeviceStroeController {

	private DeviceStroeDao dsd;
	private ReceiveLogDao rld;
	private HasDeviceDao hdd;
	public DeviceStroeDao getDsd() {
		return dsd;
	}
	@Resource(name="deviceStroeDao")
	public void setDsd(DeviceStroeDao dsd) {
		this.dsd = dsd;
	}
	public ReceiveLogDao getRld() {
		return rld;
	}
	@Resource(name="receiveLogDao")
	public void setRld(ReceiveLogDao rld) {
		this.rld = rld;
	}
	public HasDeviceDao getHdd() {
		return hdd;
	}
	@Resource(name="hasDeviceDao")
	public void setHdd(HasDeviceDao hdd) {
		this.hdd = hdd;
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
        
        for(DeviceStroe ds : dss) {
        	ds.setDeviceType(cDeviceT(ds.getDeviceType()));
        }
        
        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        jo.addProperty("dss", gson.toJson(dss));
        
        out.print(jo.toString());
        out.flush();
        out.close();
	}
	private String cDeviceT(String deviceType) {
		switch (deviceType) {
		case "0":
			return "有线";
		case "1":
			return "无线";
		default:
			return "类型出错";
		}
	}
	
	/**
	 * 申请出库
	 * @param request
	 * @param response
	 * @param userId 申请人id
	 * @param deviceId 设备id
	 */
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	public void applyDevice(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="userId") String userId,  @RequestParam(value="deviceId") String deviceId) {
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        
        boolean success = false;
        String reason = "";
        
        PrintWriter out= null;
        try {
			out = response.getWriter();
		} catch (IOException e) {}


        if(userId != null && userId != "") {
        	if(deviceId != null && deviceId != "") {
        		ReceiveLog rl = new ReceiveLog();
        		rl.setApplyId(userId);
        		rl.setDeviceId(deviceId);
        		rl.setHandleTime(new Timestamp(System.currentTimeMillis()));
        		
        		if(rld.saveReceiveLog(rl)) {
        			success = true;
        		} else
        			reason = "申请失败";
        	} else
        		reason = "请选择一个设备";
        } else
        	reason = "请先进行登陆";
        
        JsonObject jo = new JsonObject();
        jo.addProperty("success", success);
        jo.addProperty("reason", reason);
        
        out.print(jo.toString());
        out.flush();
        out.close();
	}
	//getDevicesBySelf
	@RequestMapping(value="/getDevicesBySelf", method=RequestMethod.POST)
	public void getDevicesBySelf(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="userId") String userId) {
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out= null;
        try {
			out = response.getWriter();
		} catch (IOException e) {}
        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        boolean success = false;
        String reason = "";
        
        List<DeviceStroe> dss = null; 
        
        if(userId != null) {
        
        	List<HasDevice> hds = hdd.getAllHasDeviceByPersonId(userId);
        	
        	if(hds != null) {
        		success = true;
        		dss = new ArrayList<>();
        		for(int i = 0; i < hds.size(); i++) {
        			dss.add(dsd.getDeviceStroe(hds.get(i).getDeviceId()));
        		}
        		
        		for(DeviceStroe ds : dss) {
                	ds.setDeviceType(cDeviceT(ds.getDeviceType()));
                }
        		
        		jo.addProperty("dss", gson.toJson(dss));
        	}
        } else
        	reason = "请先进行登陆";
      
        jo.addProperty("success", success);
        jo.addProperty("reason", reason);
        
        out.print(jo.toString());
        out.flush();
        out.close();
	}
	
}
