/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.hbasedao.bill;

import java.util.List;
import com.min.model.V2DbXdTransactions;

/**
 * 账单记录DAO接口
 * @author dddd
 * @version 2017-11-03
 */
public interface V2DbXdTransactionsDao {
	List<V2DbXdTransactions> getContacts(String baseinfo_id);
}