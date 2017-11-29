/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.hbasedao.bill;

import java.util.Map;

/**
 * old_billsDAO接口
 * 
 * @author dddd
 * @version 2017-11-03
 */
public interface V2DbBillsDao {
	/*
	 * 根据客户cid获取账单信息
	 */
	Map<String, Object> getMxOldBills(String cid, int limit, String lastRowkey);

	/*
	 * 账单记录DAO接口
	 */
	Map<String, Object> getDbXdTransactions(String baseinfo_id, int limit, String lastRowkey);

	Map<String, Object> getMoRecordsBill(String baseinfo_id, int limit, String lastRowKey);

	Map<String, Object> getOperatorBill(String phoneid, int limit, String lastRowKey);
}