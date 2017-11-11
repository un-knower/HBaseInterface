package com.min.hbasedao.sms;

import java.util.List;

import com.min.model.call.V2DbMxBase;
import com.min.model.sms.V2DbMxOldSmses;

public interface V2DbMxOldSmsesDao {

	/*
	 * 根据客户cid获取语音详情
	 */
	V2DbMxBase getV2DbMxBase(String cid);
	
	/*
	 * 根据客户的baseinfo_id获取V2_DB_MX_OLD_SMSES表信息
	 */
	List<V2DbMxOldSmses> getV2DbMxOldSmses(String baseinfo_id);
}
