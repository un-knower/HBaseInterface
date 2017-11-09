package com.min.service.net;

import java.util.List;

import com.min.model.net.V2DbMxNet;

public interface V2NetService {
	/*
	 * 上网记录列表
	 */
	List<V2DbMxNet> getMxOldNets(String cid, String addTime);
}
