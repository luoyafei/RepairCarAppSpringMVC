package com.repair.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_order")
public class RepairOrder {
	
	private String orderId;//订单id
	private String userId;//维修工程师Id
	private String carId;//汽车id
	private String carOwnerId;//车主id
	private Timestamp orderTime;//预约时间
	private String orderAddr;//预约地点
	private String orderState;//订单状态（0：新单，1：上级公司确认，2：派单确认，3：挂起，4：正在处理，5：成功处理；6提交结算；7结算完成8订单撤销
	private String orderType;//单据类型；0：安装单；1：拆除单；2：维修单3:加装单
	private String orderRemark;//备注
	private String suspendReason;//挂起原因
	private String suspendRemark;//挂起备注
	private String cancelOrderReason;//取消订单原因
	private String deelPlace;//现场处理地点
	private String totalMoney;//总金额
	private String settlementRemark;//结算备注	
	private String evaluateLevel;//评价等级
	private String orderEvaluate;//评价
	
	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCarId() {
		return carId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public String getCarOwnerId() {
		return carOwnerId;
	}
	public void setCarOwnerId(String carOwnerId) {
		this.carOwnerId = carOwnerId;
	}
	public Timestamp getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Timestamp orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderAddr() {
		return orderAddr;
	}
	public void setOrderAddr(String orderAddr) {
		this.orderAddr = orderAddr;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderRemark() {
		return orderRemark;
	}
	public void setOrderRemark(String orderRemark) {
		this.orderRemark = orderRemark;
	}
	public String getSuspendReason() {
		return suspendReason;
	}
	public void setSuspendReason(String suspendReason) {
		this.suspendReason = suspendReason;
	}
	public String getSuspendRemark() {
		return suspendRemark;
	}
	public void setSuspendRemark(String suspendRemark) {
		this.suspendRemark = suspendRemark;
	}
	public String getCancelOrderReason() {
		return cancelOrderReason;
	}
	public void setCancelOrderReason(String cancelOrderReason) {
		this.cancelOrderReason = cancelOrderReason;
	}
	public String getDeelPlace() {
		return deelPlace;
	}
	public void setDeelPlace(String deelPlace) {
		this.deelPlace = deelPlace;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getSettlementRemark() {
		return settlementRemark;
	}
	public void setSettlementRemark(String settlementRemark) {
		this.settlementRemark = settlementRemark;
	}
	public String getEvaluateLevel() {
		return evaluateLevel;
	}
	public void setEvaluateLevel(String evaluateLevel) {
		this.evaluateLevel = evaluateLevel;
	}
	public String getOrderEvaluate() {
		return orderEvaluate;
	}
	public void setOrderEvaluate(String orderEvaluate) {
		this.orderEvaluate = orderEvaluate;
	}
}
