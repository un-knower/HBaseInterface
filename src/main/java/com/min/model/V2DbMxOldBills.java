/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.model;


/**
 * old_billsEntity
 * @author dddd
 * @version 2017-11-03
 */
public class V2DbMxOldBills {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	private String userid;		// userid
	private String cid;		// cid
	private String addtime;		// addtime
	private String baseinfoId;		// baseinfo_id
	private String point;		// point
	private String billMonth;		// bill_month
	private String billStartDate;		// bill_start_date
	private String billEndDate;		// bill_end_date
	private String baseFee;		// base_fee
	private String extraServiceFee;		// extra_service_fee
	private String voiceFee;		// voice_fee
	private String smsFee;		// sms_fee
	private String webFee;		// web_fee
	private String extraFee;		// extra_fee
	private String totalFee;		// total_fee
	private String discount;		// discount
	private String extraDiscount;		// extra_discount
	private String actualfee;		// actualfee
	private String paidFee;		// paid_fee
	private String unpaidFee;		// unpaid_fee
	private String lastPoint;		// last_point
	private String relatedMobiles;		// related_mobiles
	private String notes;		// notes
	
	public V2DbMxOldBills() {
		super();
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String string) {
		this.userid = string;
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

	public void setAddtime(String string) {
		this.addtime = string;
	}
	
	public String getBaseinfoId() {
		return baseinfoId;
	}

	public void setBaseinfoId(String string) {
		this.baseinfoId = string;
	}
	
	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
	
	public String getBillMonth() {
		return billMonth;
	}

	public void setBillMonth(String billMonth) {
		this.billMonth = billMonth;
	}
	
	public String getBillStartDate() {
		return billStartDate;
	}

	public void setBillStartDate(String billStartDate) {
		this.billStartDate = billStartDate;
	}
	
	public String getBillEndDate() {
		return billEndDate;
	}

	public void setBillEndDate(String billEndDate) {
		this.billEndDate = billEndDate;
	}
	
	public String getBaseFee() {
		return baseFee;
	}

	public void setBaseFee(String baseFee) {
		this.baseFee = baseFee;
	}
	
	public String getExtraServiceFee() {
		return extraServiceFee;
	}

	public void setExtraServiceFee(String extraServiceFee) {
		this.extraServiceFee = extraServiceFee;
	}
	
	public String getVoiceFee() {
		return voiceFee;
	}

	public void setVoiceFee(String voiceFee) {
		this.voiceFee = voiceFee;
	}
	
	public String getSmsFee() {
		return smsFee;
	}

	public void setSmsFee(String smsFee) {
		this.smsFee = smsFee;
	}
	
	public String getWebFee() {
		return webFee;
	}

	public void setWebFee(String webFee) {
		this.webFee = webFee;
	}
	
	public String getExtraFee() {
		return extraFee;
	}

	public void setExtraFee(String extraFee) {
		this.extraFee = extraFee;
	}
	
	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	
	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	public String getExtraDiscount() {
		return extraDiscount;
	}

	public void setExtraDiscount(String extraDiscount) {
		this.extraDiscount = extraDiscount;
	}
	
	public String getActualfee() {
		return actualfee;
	}

	public void setActualfee(String actualfee) {
		this.actualfee = actualfee;
	}
	
	public String getPaidFee() {
		return paidFee;
	}

	public void setPaidFee(String paidFee) {
		this.paidFee = paidFee;
	}
	
	public String getUnpaidFee() {
		return unpaidFee;
	}

	public void setUnpaidFee(String unpaidFee) {
		this.unpaidFee = unpaidFee;
	}
	
	public String getLastPoint() {
		return lastPoint;
	}

	public void setLastPoint(String lastPoint) {
		this.lastPoint = lastPoint;
	}
	
	public String getRelatedMobiles() {
		return relatedMobiles;
	}

	public void setRelatedMobiles(String relatedMobiles) {
		this.relatedMobiles = relatedMobiles;
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}