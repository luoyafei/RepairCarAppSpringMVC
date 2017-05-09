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

import com.repair.bean.HasDevice;

@Repository("hasDeviceDao")
public class HasDeviceDao {

	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveFeedBack(HasDevice hd) {
		try {
			hibernateTemplate.save(hd);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	
	public List<HasDevice> getAllHasDeviceByPersonId(final String personId) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<HasDevice>>() {
				@SuppressWarnings("unchecked")
				@Override
				public List<HasDevice> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from HasDevice d where d.personId = :personId";
					return session.createQuery(hql).setString("personId", personId).list();
				}
			});
		} catch (Exception e) {
			return null;
		}
	}
}
