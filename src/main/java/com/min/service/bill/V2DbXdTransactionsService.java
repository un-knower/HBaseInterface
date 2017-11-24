package com.min.service.bill;

import java.util.List;

import com.min.model.V2DbXdTransactions;

public interface V2DbXdTransactionsService {

	/*
	 * 根据客户baseinfo_id获取账单记录
	 */
	List<V2DbXdTransactions> getContacts(String baseinfo_id);
}
