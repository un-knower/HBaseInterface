/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.service.bill;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.min.hbasedao.bill.V2DbBillsDao;

/**
 * old_billsService
 * 
 * @author dddd
 * @version 2017-11-03
 */
@Service
@Transactional(readOnly = true)
public class V2DbBillsServiceImp implements V2DbBillsService {

	@Autowired
	private V2DbBillsDao mxOldBiDao;

	public Map<String, Object> getMxOldBills(String cid, int limit, String lastRowkey) {
		// TODO Auto-generated method stub
		return mxOldBiDao.getMxOldBills(cid, limit, lastRowkey);
	}

	public Map<String, Object> getDbXdTransactions(String baseinfo_id, int limit, String lastRowkey) {
		// TODO Auto-generated method stub
		return mxOldBiDao.getDbXdTransactions(baseinfo_id, limit, lastRowkey);
	}

	public Map<String, Object> getMoRecordsBill(String baseinfo_id, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return mxOldBiDao.getMoRecordsBill(baseinfo_id, limit, lastRowKey);
	}

	public Map<String, Object> getOperatorBill(String phoneid, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return mxOldBiDao.getOperatorBill(phoneid, limit, lastRowKey);
	}
}