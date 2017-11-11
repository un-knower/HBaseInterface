package com.min.service.call;

import java.util.List;

import com.min.model.call.V2DbMxBase;
import com.min.model.call.V2DbMxOldCalls;

public interface V2DbMxOldCallsService {

	/*
	 * 根据客户的ID关联获取v2_db_mx_base表
	 */
	V2DbMxBase getV2DbMxBase(String cid);
	
	/*
	 * 根据客户的baseinfo_id获取V2DbMxOldCalls表
	 */
	List<V2DbMxOldCalls> getV2DbMxOldCalls(String baseinfo_id);
}
