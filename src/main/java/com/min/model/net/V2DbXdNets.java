package com.min.model.net;

/**
 * V2_DB_XD_NETS表实体类
 * @author Administrator
 * dddd 2017-11-13
 */
public class V2DbXdNets {

	private String userid; 			//userid
	private String cid;			 	//cid
	private String addtime; 		//addtime
	private String baseinfoId; 		//baseinfo_Id
	private String cellPhone; 		//cell_Phone
	private String startTime;		//start_Time
	private String updateTime;	  	//update_Time
	private String subtotal; 		//subtotal
	private String place; 			//place
	private String netType; 		//net_Type
	private String useTime; 		//use_Time
	private String subflow; 		//subflow
	
	public V2DbXdNets() {
		super();
	}
	
	public V2DbXdNets(String userid, String cid, String addtime, String baseinfoId, String cellPhone, String startTime,
			String updateTime, String subtotal, String place, String netType, String useTime, String subflow) {
		super();
		this.userid = userid;
		this.cid = cid;
		this.addtime = addtime;
		this.baseinfoId = baseinfoId;
		this.cellPhone = cellPhone;
		this.startTime = startTime;
		this.updateTime = updateTime;
		this.subtotal = subtotal;
		this.place = place;
		this.netType = netType;
		this.useTime = useTime;
		this.subflow = subflow;
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

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public String getSubflow() {
		return subflow;
	}

	public void setSubflow(String subflow) {
		this.subflow = subflow;
	}
	
	
	
}
