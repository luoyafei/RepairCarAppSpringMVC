package com.repair.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_online")
public class ToOnline {

	private String indexId;
	private String userId;
	private String onlineContent = "";
	private String carPicture = "";
	private Timestamp createDate;
	private String remark = "";
	
	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getIndexId() {
		return indexId;
	}
	public void setIndexId(String indexId) {
		this.indexId = indexId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOnlineContent() {
		return onlineContent;
	}
	public void setOnlineContent(String onlineContent) {
		this.onlineContent = onlineContent;
	}
	public String getCarPicture() {
		return carPicture;
	}
	public void setCarPicture(String carPicture) {
		this.carPicture = carPicture;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
