package com.min.hbasedao;

import java.util.List;

import com.min.model.V2DbContact;
import com.min.model.V2DbMxNet;
import com.min.model.V2ZScustomerInfo;

public interface V2DbContactDao {
	/*
	 * 根据客户cid获取通讯录列表
	 */
	List<V2DbContact> getContacts(String cid, String addTime);

	/*
	 * 上网记录列表
	 */
	List<V2DbMxNet> getMxOldNets(String cid, String addTime);

	/*
	 * 根据客户cid获取通讯录列表
	 */
	V2ZScustomerInfo getCustomr(String idcard, String siteid);
}
