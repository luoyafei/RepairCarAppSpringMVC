package com.repair.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.repair.dao.OnlineDao;
import com.repair.dao.UserDao;

@Controller
@RequestMapping("/online")
public class OnlineController {

	private UserDao ud;
	private OnlineDao od;
	
	public UserDao getUd() {
		return ud;
	}
	@Resource(name="userDao")
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	public OnlineDao getOd() {
		return od;
	}
	@Resource(name="onlineDao")
	public void setOd(OnlineDao od) {
		this.od = od;
	}
	
}
