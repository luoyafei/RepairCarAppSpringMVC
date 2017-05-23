package com.repair.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
@RequestMapping("/fundCount")
public class FundCountController {

	@RequestMapping(value="/getYear", method=RequestMethod.POST)
	public void getYear(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="year") String year) {
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out= null;
        try {
			out = response.getWriter();
		} catch (IOException e) {}

        int[] yc = {25, 11, 10, 70, 39, 85, 98, 12, 65, 69, 61, 43};
        
        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        jo.add("yc", gson.toJsonTree(yc));
        
        out.print(jo.toString());
        out.flush();
        out.close();
	}
	
	@RequestMapping(value="/getMonth", method=RequestMethod.POST)
	public void getMonth(HttpServletRequest request, HttpServletResponse response, @RequestParam(value="month") String month) {
		response.setContentType("application/json");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        
        PrintWriter out= null;
        try {
			out = response.getWriter();
		} catch (IOException e) {}

        int[] mc = {45, 30, 10, 90, 30, 95, 98, 12, 35, 49, 62, 23, 98, 19, 65, 49, 62, 13, 16, 20, 10, 10, 78, 45, 83, 19, 45, 43, 66, 33};
        
        Gson gson = new Gson();
        JsonObject jo = new JsonObject();
        jo.add("mc", gson.toJsonTree(mc));
        
        out.print(jo.toString());
        out.flush();
        out.close();
	}
}
