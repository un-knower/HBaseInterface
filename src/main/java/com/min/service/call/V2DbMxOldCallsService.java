package com.min.service.call;

import java.util.Map;
import com.min.model.call.V2DbMxBase;

public interface V2DbMxOldCallsService {

	/*
	 * 根据客户的ID关联获取v2_db_mx_base表
	 */
	V2DbMxBase getV2DbMxBase(String cid);
	
	/*
	 * 根据客户的baseinfo_id获取V2DbMxOldCalls表
	 */
	Map<String, Object> getV2DbMxOldCalls(String baseinfo_id, int limit, String lastRowkey);
}
