package com.min.model;

import java.util.ArrayList;
import java.util.List;

public class JSON {
	private String msg;
	private String code;
	private List<V2DbContact> data = new ArrayList<V2DbContact>();

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

	public List<V2DbContact> getData() {
		return data;
	}

	public void setData(List<V2DbContact> data) {
		this.data = data;
	}
}
