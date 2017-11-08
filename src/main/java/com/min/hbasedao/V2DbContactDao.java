package com.min.hbasedao;

import java.util.List;

import com.min.model.V2DbContact;
import com.min.model.V2DbMoBase;
import com.min.model.V2DbMxNet;

import com.min.model.V2DbOperatorCall;

import com.min.model.V2DbOperatorTask;
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

	/*
	 * 根据客户的ID关联获取v2_db_mo_base表
	 */
	V2DbMoBase getV2DbMoBase(String cid, String addtime);

	/*
	 * 根据客户的ID关联获取V2DbOperatorCall表的信息
	 */
	List<V2DbOperatorCall> getV2DbOperatorCall(String cid, String addtime);
/*
	 * 根据客户cid获取OperatorTask
	 */
	V2DbOperatorTask getOperatorTask(String cid, String addTime);
}
