package com.min.service.call;

import java.util.List;

import com.min.model.V2DbOperatorTask;
import com.min.model.V2ZScustomerInfo;
import com.min.model.call.V2DbContact;
import com.min.model.call.V2DbMoBase;
import com.min.model.call.V2DbMoRecordsCall;
import com.min.model.call.V2DbOperatorCall;

public interface V2CallService {

	/*
	 * 根据客户cid获取通讯录列表
	 */
	List<V2DbContact> getContacts(String cid, String addTime);

	/*
	 * 根据身份证号和平台id获取客户信息
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

	/*
	 * 根据baseinfoid获取通话记录
	 */
	List<V2DbMoRecordsCall> getV2DbMoRecordsCall(String baseInfoId, String addtime);
}
