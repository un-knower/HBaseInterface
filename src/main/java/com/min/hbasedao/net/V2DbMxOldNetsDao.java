package com.min.hbasedao.net;

import java.util.List;

import com.min.model.call.V2DbMxBase;
import com.min.model.net.V2DbMxOldNets;

public interface V2DbMxOldNetsDao {
	
	/*
	 * 上网记录列表
	 */
	List<V2DbMxOldNets> getV2DbMxOldNets(String baseinfo_id);
	
	/*
	 * 获取中间表信息
	 */
	public V2DbMxBase getV2DbMxBase(String cid);
}
