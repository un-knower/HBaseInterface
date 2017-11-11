package com.min.model.sms;

/**
 * V2_DB_XD_SMSES表Entity
 * 
 * @author dddd
 * @version 2017-11-10
 */
public class V2DbXdSmses {

	private String userid; 					// userid
	private String cid; 					// cid
	private String addtime; 				// addtime
	private String baseinfoId; 				// baseinfo_Id
	private String cellPhone; 				// cell_Phone
	private String startTime;				//start_Time
	private String updateTime;				//update_Time
	private String subtotal;				//subtotal
	private String place;					//place
	private String initType;				//init_Type
	private String otherCellPhone;			//other_Cell_Phone
	
	public V2DbXdSmses() {
		super();
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

	public String getBaseinfoId() {
		return baseinfoId;
	}

	public void setBaseinfoId(String baseinfoId) {
		this.baseinfoId = baseinfoId;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getInitType() {
		return initType;
	}

	public void setInitType(String initType) {
		this.initType = initType;
	}

	public String getOtherCellPhone() {
		return otherCellPhone;
	}

	public void setOtherCellPhone(String otherCellPhone) {
		this.otherCellPhone = otherCellPhone;
	}
	
	
}
