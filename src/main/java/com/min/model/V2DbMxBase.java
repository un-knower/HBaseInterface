/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.model;


/**
 * 申请人基本信息Entity
 * @author dddd
 * @version 2017-11-10
 */
public class V2DbMxBase {
	
	
	private String id;			// id
	private String userid;		// userid
	private String cid;		// cid
	private String name;		// name
	private String mobile;		// mobile
	private String idcard;		// idcard
	private String addtime;		// addtime
	private String carrier;		// carrier
	private String carrierName;		// carrier_name
	private String province;		// province
	private String status;		// status
	private String channel;		// channel
	private String isStatus;		// is_status
	private String isDownload;		// is_download
	private String taskId;		// task_id
	private String channelName;		// channel_name
	private String realNameRequired;		// real_name_required
	private String resetPwdSms;		// reset_pwd_sms
	
	public V2DbMxBase() {
		super();
	}
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
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
	
	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	
	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	
	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	public String getIsStatus() {
		return isStatus;
	}

	public void setIsStatus(String isStatus) {
		this.isStatus = isStatus;
	}
	
	public String getIsDownload() {
		return isDownload;
	}

	public void setIsDownload(String isDownload) {
		this.isDownload = isDownload;
	}
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
	
	public String getRealNameRequired() {
		return realNameRequired;
	}

	public void setRealNameRequired(String realNameRequired) {
		this.realNameRequired = realNameRequired;
	}
	
	public String getResetPwdSms() {
		return resetPwdSms;
	}

	public void setResetPwdSms(String resetPwdSms) {
		this.resetPwdSms = resetPwdSms;
	}
	
}