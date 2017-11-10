package com.min.hbasedao.net;

import java.util.List;
import com.min.model.net.V2DbMxNet;

public interface V2DBNetsDao {
	/*
	 * 上网记录列表
	 */
	List<V2DbMxNet> getMxOldNets(String cid, String addTime);
}
