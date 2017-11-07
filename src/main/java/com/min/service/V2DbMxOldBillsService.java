package com.min.service;

import java.util.List;



import com.min.model.V2DbMxOldBills;
import com.min.model.V2ZScustomerInfo;

public interface V2DbMxOldBillsService {

	/*
	 * 客户信息查询
	 */
	V2ZScustomerInfo getCustomr(V2ZScustomerInfo info);

	/*
	 * 根据客户cid获取账单记录
	 */
	List<V2DbMxOldBills> getContacts(String cid);
}
