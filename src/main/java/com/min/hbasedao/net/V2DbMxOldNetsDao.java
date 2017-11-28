package com.min.hbasedao.net;

import java.util.Map;

public interface V2DbMxOldNetsDao {
	
	/*
	 * 上网记录列表
	 */
	Map<String, Object> getV2DbMxOldNets(String baseinfo_id);
	
	/*
	 * 获取中间表信息
	 */
	public Map<String, Object> getV2DbMxBase(String cid);
}
