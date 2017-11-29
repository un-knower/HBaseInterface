package com.min.hbasedao.sms;

import java.util.Map;

/**
 * 运营商A短信DAO接口
 * 
 * @author dddd
 * @version 2017-11-10
 */
public interface V2DbSmsDao {

	/*
	 * 根据V2DbMoBase表中baseinfo_id获取V2DbMoRecordsSms表信息
	 */
	Map<String, Object> getV2DbMoRecordsSms(String baseinfo_id, int limit, String lastRowkeys);

	/*
	 * 根据V2_DB_OPERATOR_TASK表中baseinfo_id获取V2_DB_OPERATOR_SMS表信息
	 */
	Map<String, Object> getV2DbXdSmses(String baseinfo_id, int limit, String lastRowkeys);

	/*
	 * 根据客户的baseinfo_id获取V2_DB_MX_OLD_SMSES表信息
	 */
	Map<String, Object> getV2DbMxOldSmses(String baseinfo_id, int limit, String lastRowkeys);

	/*
	 * 根据V2_DB_OPERATOR_TASK表中baseinfo_id获取V2_DB_OPERATOR_SMS表信息
	 */
	Map<String, Object> getV2DbOperatorSms(String phoneid, int limit, String lastRowkeys);
}
