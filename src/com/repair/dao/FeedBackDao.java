package com.repair.dao;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.repair.bean.FeedBack;

@Repository("feedBackDao")
public class FeedBackDao {

	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveFeedBack(FeedBack fd) {
		try {
			hibernateTemplate.save(fd);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	
}
