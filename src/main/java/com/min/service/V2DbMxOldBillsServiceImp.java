/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.dao.V2ZsCustomInfoDao;
import com.min.hbasedao.V2DbMxOldBillsDao;
import com.min.hbasedao.V2DbXdTransactionsDao;
import com.min.model.V2DbMxOldBills;
import com.min.model.V2DbXdTransactions;
import com.min.model.V2ZScustomerInfo;



/**
 * old_billsService
 * @author dddd
 * @version 2017-11-03
 */
@Service
@Transactional(readOnly = true)
public class V2DbMxOldBillsServiceImp implements V2DbMxOldBillsService{

	@Autowired
	private V2ZsCustomInfoDao custDao;
	@Autowired
	private V2DbMxOldBillsDao mxOldBiDao;

	public V2ZScustomerInfo getCustomr(V2ZScustomerInfo info) {
		return custDao.getCustomr(info);
	}

	public List<V2DbMxOldBills> getContacts(String cid) {
		return mxOldBiDao.getContacts(cid);
	}
}