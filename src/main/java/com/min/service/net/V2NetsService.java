package com.min.service.net;

import java.util.Map;

public interface V2NetsService {
	
	/*
	 * 上网记录列表
	 */
	Map<String, Object> getV2DbMxOldNets(String baseinfo_id, int limit, String lastRowKey);

	/*
	 * 运营商C用户上网记录列表
	 */
	Map<String, Object> getV2DbXdNets(String baseinfo_id, int limit, String lastRowKey);

}
