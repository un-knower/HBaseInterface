package com.min.model.sms;

public class V2DbOperatorTask {

	/**
	 * V2_DB_OPERATOR_TASK表Entity,用于短信运营商B
	 * @author dddd
	 * @version 2017-11-11
	 */
	
	private String id;									// id
	private String userId;								// userid
	private String cid;									// cid
	private String addTime;								// addtime
	private String name;								// name
	private String idCard;								// idcard
	private String mobile;								// mobile
	private String uuid;								// uuid
	private String phoneId;								// phoneId
	private String monthRecordDetailMap;				// monthRecordDetailMap
	private String isStart;								// is_Start
	private String isDownLoaded;						// is_DownLoaded
	private String taskId;								// location_type
	
	
	public V2DbOperatorTask() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
	}
	public String getMonthRecordDetailMap() {
		return monthRecordDetailMap;
	}
	public void setMonthRecordDetailMap(String monthRecordDetailMap) {
		this.monthRecordDetailMap = monthRecordDetailMap;
	}
	public String getIsStart() {
		return isStart;
	}
	public void setIsStart(String isStart) {
		this.isStart = isStart;
	}
	public String getIsDownLoaded() {
		return isDownLoaded;
	}
	public void setIsDownLoaded(String isDownLoaded) {
		this.isDownLoaded = isDownLoaded;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	
}
