/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.hbasedao;

import java.util.List;


import com.min.model.V2DbMxOldBills;


/**
 * old_billsDAO接口
 * @author dddd
 * @version 2017-11-03
 */
public interface V2DbMxOldBillsDao {
	/*
	 * 根据客户cid获取账单信息
	 */
	List<V2DbMxOldBills> getContacts(String cid, String addTime);
}