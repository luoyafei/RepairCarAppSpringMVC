package com.repair.bean;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_feedBack")
public class FeedBack {
	
	private String feedBackId;
	private Timestamp backTime;
	private String feedContent;//反馈内容
	private String personId;//反馈人员Id;
	private String isHandle;//是否处理
	private String result;//处理结果

	@Id
	@GenericGenerator(name="uuid", strategy="uuid")
	@GeneratedValue(generator="uuid")
	public String getFeedBackId() {
		return feedBackId;
	}
	public void setFeedBackId(String feedBackId) {
		this.feedBackId = feedBackId;
	}
	public Timestamp getBackTime() {
		return backTime;
	}
	public void setBackTime(Timestamp backTime) {
		this.backTime = backTime;
	}
	public String getFeedContent() {
		return feedContent;
	}
	public void setFeedContent(String feedContent) {
		this.feedContent = feedContent;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String PersonId) {
		this.personId = PersonId;
	}
	public String getIsHandle() {
		return isHandle;
	}
	public void setIsHandle(String isHandle) {
		this.isHandle = isHandle;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
