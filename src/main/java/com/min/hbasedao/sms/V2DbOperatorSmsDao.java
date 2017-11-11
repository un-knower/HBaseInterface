package com.min.hbasedao.sms;

import java.util.List;

import com.min.model.sms.V2DbOperatorSms;
import com.min.model.sms.V2DbOperatorTask;

public interface V2DbOperatorSmsDao {

	/*
	 * 获取V2_DB_OPERATOR_TASK表信息
	 */
	V2DbOperatorTask getV2DbOperatorTask(String cid);
	
	/*
	 * 根据V2_DB_OPERATOR_TASK表中baseinfo_id获取V2_DB_OPERATOR_SMS表信息
	 */
	List<V2DbOperatorSms> getV2DbOperatorSms(String task_id);
}
