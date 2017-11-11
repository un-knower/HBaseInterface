package com.min.hbasedao.sms;

import java.util.List;

import com.min.model.sms.V2DbXdBase;
import com.min.model.sms.V2DbXdSmses;

public interface V2DbXdSmsesDao {

	/*
	 * 获取V2_DB_XD_BASE表信息
	 */
	V2DbXdBase getV2DbXdBase(String cid);
	
	/*
	 * 根据V2_DB_OPERATOR_TASK表中baseinfo_id获取V2_DB_OPERATOR_SMS表信息
	 */
	List<V2DbXdSmses> getV2DbXdSmses(String baseinfo_id);
}
