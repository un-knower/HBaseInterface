package com.min.model;

import java.util.ArrayList;
import java.util.List;

public class JSON_MxOldBills {

	private String msg;
	private String code;

	private List<V2DbMxOldBills> data = new ArrayList<V2DbMxOldBills>();
	
	


	public List<V2DbMxOldBills> getData() {
		return data;
	}

	public void setData(List<V2DbMxOldBills> data) {
		this.data = data;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
