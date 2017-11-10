package com.min.service.mx_old_calls;

import java.util.List;


import com.min.model.mx_old_calls.V2DbMxBase;
import com.min.model.mx_old_calls.V2DbMxOldCalls;

public interface V2DbMxOldCallsService {

	/*
	 * 根据客户的ID关联获取v2_db_mx_base表
	 */
	V2DbMxBase getV2DbMxBase(String cid, String addtime);
	
	/*
	 * 根据客户的baseinfo_id获取V2DbMxOldCalls表
	 */
	List<V2DbMxOldCalls> getV2DbMxOldCalls(String baseinfo_id, String addtime);
}
