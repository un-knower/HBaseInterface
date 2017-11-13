package com.min.service.sms;

import java.util.List;

import com.min.model.sms.V2DbXdBase;
import com.min.model.sms.V2DbXdSmses;

public interface V2DbXdSmsesService {

	/*
	 * 根据客户的ID关联获取V2_DB_XD_BASE表
	 */
	List<V2DbXdBase> getV2DbXdBase(String cid);
	
	/*
	 * 根据客户的baseinfo_id获取V2_DB_XD_SMSES表
	 */
	List<V2DbXdSmses> getV2DbXdSmses(String baseinfo_id);
}
