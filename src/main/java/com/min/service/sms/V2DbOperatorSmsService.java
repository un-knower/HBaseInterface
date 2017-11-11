package com.min.service.sms;

import java.util.List;

import com.min.model.sms.V2DbOperatorSms;
import com.min.model.sms.V2DbOperatorTask;

public interface V2DbOperatorSmsService {

	/*
	 * 根据客户的ID关联获取V2_DB_OPERATOR_TASK表baseinfo_id字段
	 */
	V2DbOperatorTask getV2DbOperatorTask(String cid);
	
	/*
	 * 根据客户的baseinfo_id获取V2DbOperatorSms表
	 */
	List<V2DbOperatorSms> getV2DbOperatorSms(String task_id);
}
