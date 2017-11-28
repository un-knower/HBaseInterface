package com.min.service.sms;

import java.util.Map;
import com.min.model.call.V2DbMxBase;

public interface V2DbMxOldSmsesService {

	/*
	 * 根据客户cid获取语音详情
	 */
	V2DbMxBase getV2DbMxBase(String cid);
	
	/*
	 * 根据客户的baseinfo_id获取V2_DB_MX_OLD_SMSES表信息
	 */
	Map<String, Object> getV2DbMxOldSmses(String baseinfo_id);
}
