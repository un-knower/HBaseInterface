package com.min.service;

import java.util.List;



import com.min.model.V2DbMxOldBills;

public interface V2DbMxOldBillsService {


	/*
	 * 根据客户cid获取账单记录
	 */
	List<V2DbMxOldBills> getContacts(String cid, String addTime);
}
