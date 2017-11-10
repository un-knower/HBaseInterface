package com.min.service.bill;

import java.util.List;
import com.min.model.V2DbMxOldBills;

public interface V2DbMxOldBillsService {

	/*
	 * 根据客户cid和addTime获取账单记录
	 */
	List<V2DbMxOldBills> getContacts(String cid);
}
