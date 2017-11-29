package com.min.hbasedao.sms;

import java.util.Map;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.sms.V2DbMoRecordsSms;
import com.min.model.sms.V2DbMxOldSmses;
import com.min.model.sms.V2DbOperatorSms;
import com.min.utils.HbaseUtils;

@Component
public class V2DbSmsDaoImp implements V2DbSmsDao {

	// 获取运营商B短信详情
	public Map<String, Object> getV2DbMoRecordsSms(String baseinfo_id, int limit, String lastRowkeys) {
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMoRecordsSms> base = new HbaseBase<V2DbMoRecordsSms>(new V2DbMoRecordsSms());
		return base.scan("V2_DB_MO_RECORDS_SMS", rowkey, "c", base);
	}

	public Map<String, Object> getV2DbXdSmses(String baseinfo_id, int limit, String lastRowkeys) {
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMoRecordsSms> base = new HbaseBase<V2DbMoRecordsSms>(new V2DbMoRecordsSms());
		return base.scan("V2_DB_XD_SMSES", rowkey, "c", base);
	}

	// 获取语音详情
	public Map<String, Object> getV2DbMxOldSmses(String baseinfo_id, int limit, String lastRowkeys) {
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMxOldSmses> base = new HbaseBase<V2DbMxOldSmses>(new V2DbMxOldSmses());
		return base.scan("V2_DB_MX_OLD_SMSES", rowkey, "c", base);
	}

	// 获取V2_DB_OPERATOR_SMS表信息
	public Map<String, Object> getV2DbOperatorSms(String task_id, int limit, String lastRowkeys) {
		if (task_id == null) {
			return HbaseUtils.returnNull();
		}
		HbaseBase<V2DbOperatorSms> base = new HbaseBase<V2DbOperatorSms>(new V2DbOperatorSms());
		return base.scan("V2_DB_OPERATOR_SMS", task_id + "|", "c", base);
	}
}
