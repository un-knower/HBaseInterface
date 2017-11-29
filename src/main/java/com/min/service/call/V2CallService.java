package com.min.service.call;

import java.util.Map;

import com.min.model.V2ZScustomerInfo;
import com.min.model.base.V2DbMoBase;
import com.min.model.base.V2DbMxBase;

public interface V2CallService {

	/*
	 * 根据客户cid获取通讯录列表
	 */
	Map<String, Object> getContacts(String cid, int limit, String lastRowKey);

	/*
	 * 根据身份证号、平台id和手机号获取客户信息
	 */
	V2ZScustomerInfo getCustomr(String idcard, String siteid, String mobile);

	/*
	 * 根据客户的ID关联获取v2_db_mo_base表
	 */
	V2DbMoBase getV2DbMoBase(String cid);

	/*
	 * 根据客户的ID关联获取V2DbOperatorCall表的信息
	 */
	Map<String, Object> getV2DbOperatorCall(String cid, int limit, String lastRowKey);

	/*
	 * 根据客户cid获取OperatorTask
	 */
	Map<String, Object> getOperatorTask(String cid, int limit, String lastRowKey);

	/*
	 * 根据客户的cid获取V2DbXdBase表
	 */
	Map<String, Object> getV2DbXdBase(String cid, int limit, String lastRowKey);

	/*
	 * 根据客户的baseinfo_id获取V2DbXdCalls表
	 */
	Map<String, Object> getV2DbXdCalls(String baseinfo_id, int limit, String lastRowKey);

	/*
	 * 根据baseinfoid获取通话记录
	 */
	Map<String, Object> getV2DbMoRecordsCall(String baseInfoId, int limit, String lastRowKey);

	/*
	 * 根据客户的ID关联获取v2_db_mx_base表
	 */
	V2DbMxBase getV2DbMxBase(String cid);

	/*
	 * 根据客户的baseinfo_id获取V2DbMxOldCalls表
	 */
	Map<String, Object> getV2DbMxOldCalls(String baseinfo_id, int limit, String lastRowkey);
}
