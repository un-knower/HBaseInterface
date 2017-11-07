package com.min.service;

import java.util.List;

import com.min.model.V2DbXdTransactions;

public interface V2DbXdTransactionsService {

	/*
	 * 根据客户cid获取账单记录
	 */
	List<V2DbXdTransactions> getContacts(String cid, String addTime);
}
