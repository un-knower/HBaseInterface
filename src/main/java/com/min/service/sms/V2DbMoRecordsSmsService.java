package com.min.service.sms;

import java.util.Map;
import com.min.model.call.V2DbMoBase;

public interface V2DbMoRecordsSmsService {

	/*
	 * 根据客户的ID关联获取v2_db_mo_base表
	 */
	V2DbMoBase getV2DbMoBase(String cid);
	
	/*
	 * 根据客户的baseinfo_id获取V2DbMoRecordsSms表
	 */
	Map<String, Object> getV2DbMoRecordsSms(String baseinfo_id);
}
