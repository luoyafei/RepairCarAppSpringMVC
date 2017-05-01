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

import com.repair.bean.Admin;

@Repository("adminDao")
public class AdminDao {
	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	/**
	 * 创建Admin账号
	 * @param admin
	 * @return
	 */
	public boolean saveAdmin(Admin admin) {
		try {
			hibernateTemplate.save(admin);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	/**
	 * 更新Admin账号
	 * @param admin
	 * @return
	 */
	public boolean updateAdmin(Admin admin) {
		try {
			hibernateTemplate.save(admin);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	/**
	 * 删除Admin账户
	 * @param admin
	 * @return
	 */
	public boolean deleteAdmin(Admin admin) {
		try {
			hibernateTemplate.delete(admin);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	
	/**
	 * 通过Id获取Admin账户
	 * @param adminId
	 * @return
	 */
	public Admin getAdminById(String adminId) {
		return hibernateTemplate.get(Admin.class, adminId);
	}
	/**
	 * 通过Admin账户名称获取整个Admin信息
	 * @param adminName
	 * @return
	 */
	public Admin getAdminByName(final String adminName) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<Admin>() {
				@Override
				public Admin doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from Admin a where a.adminName = :adminName";
					@SuppressWarnings("unchecked")
					List<Admin> as = session.createQuery(hql).setString("adminName", adminName).list();
					if(as != null && as.size() == 1)
						return as.get(0);
					else
						return null;
				}
			});
		} catch (Exception e) {
			return null;
		}
	}
}
