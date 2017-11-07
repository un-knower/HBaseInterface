package com.min.service;

import java.util.List;


import com.min.model.V2DbXdTransactions;
import com.min.model.V2ZScustomerInfo;

public interface V2DbXdTransactionsService {
	/*
	 * 客户信息查询
	 */
	V2ZScustomerInfo getCustomr(V2ZScustomerInfo info);

	/*
	 * 根据客户cid获取账单记录
	 */
	List<V2DbXdTransactions> getContacts(String cid);
}
