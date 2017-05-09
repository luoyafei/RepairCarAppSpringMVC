package com.repair.dao;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.repair.bean.DeviceStroe;

@Repository("deviceStroeDao")
public class DeviceStroeDao {

	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveFeedBack(DeviceStroe ds) {
		try {
			hibernateTemplate.save(ds);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	
	public List<DeviceStroe> getAllDevices() {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<DeviceStroe>>() {
				@Override
				public List<DeviceStroe> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from DeviceStroe";
					@SuppressWarnings("unchecked")
					List<DeviceStroe> dss = session.createQuery(hql).list();
					return dss;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}
}
