package com.min.model;

import java.util.ArrayList;
import java.util.List;

public class JSON_XdTransactions {

	private String msg;
	private String code;
	private List<V2DbXdTransactions> data = new ArrayList<V2DbXdTransactions>();
	
	

	public List<V2DbXdTransactions> getData() {
		return data;
	}

	public void setData(List<V2DbXdTransactions> data) {
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
