package com.min.service.net;

import java.util.List;

import com.min.model.V2DbXdBase;
import com.min.model.net.V2DbXdNets;

public interface V2DbXdNetsService {

	/*
	 * 运营商C用户上网记录列表
	 */
	List<V2DbXdNets> getV2DbXdNets(String baseinfo_id);
	
	/*
	 * 获取中间表信息
	 */
	public List<V2DbXdBase> getV2DbXdBase(String cid);
}
