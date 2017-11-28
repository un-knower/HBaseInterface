package com.min.service.net;

import java.util.Map;

public interface V2NetsService {
	/*
	 * 上网记录列表
	 */
	Map<String, Object> getV2DbMxOldNets(String baseinfo_id);
	
	/*
	 * 获取中间表信息
	 */
	 Map<String, Object> getV2DbMxBase(String cid);
	/*
	 * 运营商C用户上网记录列表
	 */
	Map<String, Object> getV2DbXdNets(String baseinfo_id);
	
	/*
	 * 获取中间表信息
	 */
	public Map<String, Object> getV2DbXdBase(String cid);
}
