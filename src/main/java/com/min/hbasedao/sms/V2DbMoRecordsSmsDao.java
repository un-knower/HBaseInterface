package com.min.hbasedao.sms;

import java.util.List;

import com.min.model.call.V2DbMoBase;
import com.min.model.call.V2DbMxBase;
import com.min.model.call.V2DbMxOldCalls;
import com.min.model.sms.V2DbMoRecordsSms;

/**
 * 运营商A短信DAO接口
 * @author dddd
 * @version 2017-11-10
 */
public interface V2DbMoRecordsSmsDao {

	/*
	 * 获取客户详情
	 */
	V2DbMoBase getV2DbMoBase(String cid);
	
	/*
	 * 根据V2DbMoBase表中baseinfo_id获取V2DbMoRecordsSms表信息
	 */
	List<V2DbMoRecordsSms> getV2DbMoRecordsSms(String baseinfo_id);
}
