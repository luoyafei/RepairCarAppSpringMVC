package com.repair.test;

import java.sql.Timestamp;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.repair.bean.DeviceStroe;
import com.repair.dao.DeviceStroeDao;

@ContextConfiguration("classpath:beans.xml")
public class TestDevice {

	@Resource(name="hibernateTemplate")
	private HibernateTemplate ht;
	
	@Resource(name="deviceStroeDao")
	private DeviceStroeDao dsd;
	
	@Test
	public void add() {
		System.out.println(1);
		DeviceStroe ds = new DeviceStroe();
		ds.setDeviceId(UUID.randomUUID().toString().replace("-", ""));
		ds.setDeviceNum("110");
		ds.setDeviceState("0");
		ds.setDeviceType("0");
		ds.setInputTime(new Timestamp(System.currentTimeMillis()));
		ds.setPersonId("1");
		dsd.getAllDevices();
	}
}
