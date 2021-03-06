package com.repair.dao;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.repair.bean.ToOnline;

@Repository("onlineDao")
public class OnlineDao {

	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveOnline(ToOnline to) {
		try {
			hibernateTemplate.save(to);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
}
