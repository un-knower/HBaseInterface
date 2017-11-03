package com.min.model;

import java.util.ArrayList;
import java.util.List;

public class JSON<E> {
	private String msg;
	private String code;
	private List<?> data = new ArrayList<E>();

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

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}
}
