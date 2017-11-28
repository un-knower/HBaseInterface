package com.min.hbasedao.sms;

import java.util.Map;
import com.min.model.call.V2DbMxBase;
import com.min.model.sms.V2DbOperatorTask;

public interface V2DbMxOldSmsesDao {

	/*
	 * 根据客户cid获取语音详情
	 */
	V2DbMxBase getV2DbMxBase(String cid);

	/*
	 * 根据客户的baseinfo_id获取V2_DB_MX_OLD_SMSES表信息
	 */
	Map<String, Object> getV2DbMxOldSmses(String baseinfo_id);
	/*
	 * 获取V2_DB_OPERATOR_TASK表信息
	 */
	V2DbOperatorTask getV2DbOperatorTask(String cid);
	
	/*
	 * 根据V2_DB_OPERATOR_TASK表中baseinfo_id获取V2_DB_OPERATOR_SMS表信息
	 */
	Map<String, Object> getV2DbOperatorSms(String task_id);
}
