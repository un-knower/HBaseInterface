package com.min.service.sms;

import java.util.Map;

public interface V2DbSmsService {

	/*
	 * 根据客户的baseinfo_id获取V2DbMoRecordsSms表
	 */
	Map<String, Object> getV2DbMoRecordsSms(String baseinfo_id, int limit, String lastRowKey);

	/*
	 * 根据客户的baseinfo_id获取V2_DB_MX_OLD_SMSES表信息
	 */
	Map<String, Object> getV2DbMxOldSmses(String baseinfo_id, int limit, String lastRowKey);

	/*
	 * 根据客户的task_id获取V2DbOperatorSms表
	 */
	Map<String, Object> getV2DbOperatorSms(String phoneid, int limit, String lastRowKey);

	/*
	 * 根据客户的baseinfo_id获取V2_DB_XD_SMSES表
	 */
	Map<String, Object> getV2DbXdSmses(String baseinfo_id, int limit, String lastRowKey);
}
