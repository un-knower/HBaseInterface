package com.min.service.bill;

import java.util.Map;

public interface V2DbBillsService {

	/*
	 * 根据客户cid获取账单信息
	 */
	Map<String, Object> getMxOldBills(String cid, int limit, String lastRowKey);

	/*
	 * 账单记录DAO接口
	 */
	Map<String, Object> getDbXdTransactions(String baseinfo_id, int limit, String lastRowKey);

	Map<String, Object> getMoRecordsBill(String baseinfo_id, int limit, String lastRowKey);

	Map<String, Object> getOperatorBill(String phoneid, int limit, String lastRowKey);
}
