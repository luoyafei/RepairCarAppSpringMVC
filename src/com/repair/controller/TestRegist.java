package com.repair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestRegist {

	@RequestMapping(value="regist", method=RequestMethod.POST)
	public String sayHello(@RequestParam(value="name") String name, @RequestParam(value="password") String password) {
		
System.out.println(name + ", " + password);
		return "success";
	}
}
