package com.min.hbasedao.sms;

import java.util.Map;
import com.min.model.call.V2DbMoBase;

/**
 * 运营商A短信DAO接口
 * 
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
	Map<String, Object> getV2DbMoRecordsSms(String baseinfo_id);

	/*
	 * 根据V2_DB_OPERATOR_TASK表中baseinfo_id获取V2_DB_OPERATOR_SMS表信息
	 */
	Map<String, Object> getV2DbXdSmses(String baseinfo_id);
}
