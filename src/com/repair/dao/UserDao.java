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

import com.repair.bean.User;

@Repository("userDao")
public class UserDao {
	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	/**
	 * 创建用户账号
	 * @param user
	 * @return
	 */
	public boolean saveUser(User user) {
		try {
			hibernateTemplate.save(user);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	/**
	 * 更新user账号
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user) {
		try {
			hibernateTemplate.save(user);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	/**
	 * 删除use账户
	 * @param user
	 * @return
	 */
	public boolean deleteUser(User user) {
		try {
			hibernateTemplate.delete(user);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	
	/**
	 * 通过Id获取User账户
	 * @param userId
	 * @return
	 */
	public User getUserById(String userId) {
		return hibernateTemplate.get(User.class, userId);
	}
	/**
	 * 通过User账户名称获取整个user信息
	 * @param userName
	 * @param userType
	 * @return
	 */
	public User getUserByName(final String userName, final String userType) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<User>() {
				@Override
				public User doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from User u where u.userName = :userName and u.userType = :userType";
					@SuppressWarnings("unchecked")
					List<User> as = session.createQuery(hql).setString("userName", userName).setString("userType", userType).list();
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
