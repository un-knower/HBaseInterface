/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.hbasedao.mx_old_calls;

import java.util.List;

import com.min.model.mx_old_calls.V2DbMxBase;
import com.min.model.mx_old_calls.V2DbMxOldCalls;

/**
 * 语音详情DAO接口
 * @author dddd
 * @version 2017-11-10
 */
public interface V2DbMxOldCallsDao {
	/*
	 * 根据客户cid获取语音详情
	 */
	V2DbMxBase getV2DbMxBase(String cid, String addtime);
	
	/*
	 * 根据客户的baseinfo_id获取V2DbMxOldCalls表
	 */
	List<V2DbMxOldCalls> getV2DbMxOldCalls(String baseinfo_id, String addtime);
}