package com.min.service.sms;

import java.util.Map;

public interface V2DbXdSmsesService {

	/*
	 * 根据客户的baseinfo_id获取V2_DB_XD_SMSES表
	 */
	Map<String, Object> getV2DbXdSmses(String baseinfo_id);
}
