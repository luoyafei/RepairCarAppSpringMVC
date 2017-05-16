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

import com.repair.bean.RepairOrder;

@Repository("repairDao")
public class RepairOrderDao {

	private HibernateTemplate hibernateTemplate;
	@Resource(name = "hibernateTemplate")
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public boolean saveRepairOrder(RepairOrder ro) {
		try {
			hibernateTemplate.save(ro);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean deleteRepairOrder(RepairOrder ro) {
		try {
			hibernateTemplate.delete(ro);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public boolean updateRepairOrder(RepairOrder ro) {
		try {
			hibernateTemplate.update(ro);
			return true;
		} catch(DataAccessException e) {
			return false;
		}
	}
	public RepairOrder getRepairOrderById(String orderId) {
		return hibernateTemplate.get(RepairOrder.class, orderId);
	}
	/**
	 * 
	 * @param start 
	 * @param length
	 * @param userId 工程师Id
	 * @param orderState 订单状态
	 * @param orderType 订单类型
	 * @return
	 */
	public List<RepairOrder> getRepairOrders(final int start, final int length,final String userId, final String orderState, final String orderType) {
		if(orderState == null && orderType == null) {
			return getRepairOrders(start, length);
		} else {
			try {
				return hibernateTemplate.execute(new HibernateCallback<List<RepairOrder>>() {
					@SuppressWarnings("unchecked")
					@Override
					public List<RepairOrder> doInHibernate(Session session) throws HibernateException, SQLException {
						String hql = "from RepairOrder r where r.userId = :userId and r.orderState = :orderState and r.orderType = :orderType";
						return session.createQuery(hql).setString("userId", userId).setString("orderState", orderState).setString("orderType", orderType).setFirstResult(start).setMaxResults(length).list();
					}
				});
			} catch (Exception e) {
				return null;
			}
		}
	}
	private List<RepairOrder> getRepairOrders(final int start, final int length) {
		try {
			return hibernateTemplate.execute(new HibernateCallback<List<RepairOrder>>() {
				@SuppressWarnings("unchecked")
				@Override
				public List<RepairOrder> doInHibernate(Session session) throws HibernateException, SQLException {
					String hql = "from RepairOrder";
					return session.createQuery(hql).setFirstResult(start).setMaxResults(length).list();
				}
			});
		} catch (Exception e) {
			return null;
		}
	}
}
