package com.min.service;

import java.util.List;

import com.min.model.V2DbContact;
import com.min.model.V2ZScustomerInfo;

public interface V2Service {
	/*
	 * 客户信息查询
	 */
	V2ZScustomerInfo getCustomr(V2ZScustomerInfo info);

	/*
	 * 根据客户cid获取通讯录列表
	 */
	List<V2DbContact> getContacts(String cid,String addTime);
}
