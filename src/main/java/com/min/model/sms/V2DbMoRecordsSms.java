package com.min.model.sms;


/**
 * v2DbMoRecordsSmsEntity
 * 
 * @author dddd
 * @version 2017-11-10
 */
public class V2DbMoRecordsSms {

	private String baseinfoId; 			// baseinfo_Id
	private String userid; 				// userid
	private String cid; 				// cid
	private String addtime; 			// addtime
	private String businessName; 		// business_Name
	private String startTime;			//start_Time
	private String MessageType;			//Message_Type
	private String cost;				//cost
	private String area;				//area
	private String messageLength;		//message_Length
	private String selfNumber;			//self_Number
	private String otherPartyOperator;	//other_Party_Operator
	private String otherPartyNumber;	//other_Party_Number
	private String serviceType;			//service_Type
	private String totalCost;			//total_Cost
	
	
	public V2DbMoRecordsSms() {
		super();
	}
	public String getBaseinfoId() {
		return baseinfoId;
	}
	public void setBaseinfoId(String baseinfoId) {
		this.baseinfoId = baseinfoId;
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
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getMessageType() {
		return MessageType;
	}
	public void setMessageType(String messageType) {
		MessageType = messageType;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getMessageLength() {
		return messageLength;
	}
	public void setMessageLength(String messageLength) {
		this.messageLength = messageLength;
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
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}


}
