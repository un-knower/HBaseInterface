package com.min.hbasedao.net;

import java.util.Map;

public interface V2DbXdNetsDao {

	/*
	 * 运营商C用户上网记录列表
	 */
	Map<String, Object> getV2DbXdNets(String baseinfo_id);
	
	/*
	 * 获取中间表信息
	 */
	public Map<String, Object> getV2DbXdBase(String cid);
}
