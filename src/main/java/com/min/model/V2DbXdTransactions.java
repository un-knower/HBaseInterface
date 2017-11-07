/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.model;

/**
 * 璐﹀崟璁板綍Entity
 * @author dddd
 * @version 2017-11-03
 */
public class V2DbXdTransactions {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private String userid;		// userid
	private String cid;		// cid
	private String addtime;		// addtime
	private String baseinfoId;		// baseinfo_id
	private String cellPhone;		// cell_phone
	private String totalAmt;		// total_amt
	private String updateTime;		// update_time
	private String payAmt;		// pay_amt
	private String billCycle;		// bill_cycle
	private String planAmt;		// plan_amt
	
	public V2DbXdTransactions() {
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

	public void setCid(String string) {
		this.cid = string;
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
	
	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}
	
	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}
	
	public String getBillCycle() {
		return billCycle;
	}

	public void setBillCycle(String billCycle) {
		this.billCycle = billCycle;
	}
	
	public String getPlanAmt() {
		return planAmt;
	}

	public void setPlanAmt(String planAmt) {
		this.planAmt = planAmt;
	}
	
}