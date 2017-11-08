/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.model;

/**
 * CONTACTEntity
 * 
 * @author d
 * @version 2017-11-01
 */
public class V2DbOperatorTask {
	private String id;
	private String cid; // cid
	private String userid; // userid
	private String name; // name
	private String mobile; // mobile
	private String addtime; // addtime

	private String idcard;
	private String uuid; //
	private String phoneid; //
	private String monthrecorddetailmap; //
	private String isStart; //
	private String isDownloaded; //

	public V2DbOperatorTask() {
		super();
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getPhoneid() {
		return phoneid;
	}

	public void setPhoneid(String phoneid) {
		this.phoneid = phoneid;
	}

	public String getIsStart() {
		return isStart;
	}

	public void setIsStart(String isStart) {
		this.isStart = isStart;
	}

	public String getIsDownloaded() {
		return isDownloaded;
	}

	public void setIsDownloaded(String isDownloaded) {
		this.isDownloaded = isDownloaded;
	}

	public String getMonthrecorddetailmap() {
		return monthrecorddetailmap;
	}

	public void setMonthrecorddetailmap(String monthrecorddetailmap) {
		this.monthrecorddetailmap = monthrecorddetailmap;
	}
}