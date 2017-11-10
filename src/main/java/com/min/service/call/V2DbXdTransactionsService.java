package com.min.service.call;

import java.util.List;

import com.min.model.V2DbXdTransactions;

public interface V2DbXdTransactionsService {

	/*
	 * 根据客户cid和addTime获取账单记录
	 */
	List<V2DbXdTransactions> getContacts(String cid);
}
