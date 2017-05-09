package com.repair.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 个人库存表
 * @author Diamond
 *
 */
@Entity
@Table(name="t_hasDevice")
public class HasDevice {

	private int upId;
	private String deviceId;
	private String PersonId;
	private Timestamp createDate;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getUpId() {
		return upId;
	}
	public void setUpId(int upId) {
		this.upId = upId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getPersonId() {
		return PersonId;
	}
	public void setPersonId(String PersonId) {
		this.PersonId = PersonId;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
}
