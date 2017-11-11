package com.min.service.sms;

import java.util.List;


import com.min.model.call.V2DbMoBase;
import com.min.model.sms.V2DbMoRecordsSms;

public interface V2DbMoRecordsSmsService {

	/*
	 * 根据客户的ID关联获取v2_db_mo_base表
	 */
	V2DbMoBase getV2DbMoBase(String cid);
	
	/*
	 * 根据客户的baseinfo_id获取V2DbMoRecordsSms表
	 */
	List<V2DbMoRecordsSms> getV2DbMoRecordsSms(String baseinfo_id);
}
