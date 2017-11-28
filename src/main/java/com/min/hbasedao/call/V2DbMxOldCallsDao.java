/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.hbasedao.call;
import java.util.Map;
import com.min.model.call.V2DbMxBase;

/**
 * 语音详情DAO接口
 * @author dddd
 * @version 2017-11-10
 */
public interface V2DbMxOldCallsDao {
	/*
	 * 根据客户cid获取语音详情
	 */
	V2DbMxBase getV2DbMxBase(String cid);
	
	/*
	 * 根据客户的baseinfo_id获取V2DbMxOldCalls表信息
	 */
	Map<String, Object> getV2DbMxOldCalls(String baseinfo_id);
}