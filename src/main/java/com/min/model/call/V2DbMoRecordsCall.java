/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.model.call;

/**
 * CONTACTEntity
 * 
 * @author d
 * @version 2017-11-01
 */
public class V2DbMoRecordsCall {
	private String id;
	private String baseinfoId;
	private String cid; // cid
	private String userid; // userid
	private String type; //

	private String addtime; // addtime
	private String callTime; //
	private String area;
	private String areaType;
	private String selfNumber;

	private String otherPartyOperator;
	private String otherPartyNumber;
	private String startTime;
	private String totalCost;

	public V2DbMoRecordsCall() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBaseinfoId() {
		return baseinfoId;
	}

	public void setBaseinfoId(String baseinfoId) {
		this.baseinfoId = baseinfoId;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getCallTime() {
		return callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAreaType() {
		return areaType;
	}

	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}

	public String getSelfNumber() {
		return selfNumber;
	}

	public void setSelfNumber(String selfNumber) {
		this.selfNumber = selfNumber;
	}

	public String getOtherPartyOperator() {
		return otherPartyOperator;
	}

	public void setOtherPartyOperator(String otherPartyOperator) {
		this.otherPartyOperator = otherPartyOperator;
	}

	public String getOtherPartyNumber() {
		return otherPartyNumber;
	}

	public void setOtherPartyNumber(String otherPartyNumber) {
		this.otherPartyNumber = otherPartyNumber;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
}