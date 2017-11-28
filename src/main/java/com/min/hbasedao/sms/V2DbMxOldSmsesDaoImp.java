package com.min.hbasedao.sms;

import java.util.Map;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.call.V2DbMxBase;
import com.min.model.sms.V2DbMxOldSmses;
import com.min.model.sms.V2DbOperatorSms;
import com.min.model.sms.V2DbOperatorTask;
import com.min.utils.HbaseUtils;

@Component
public class V2DbMxOldSmsesDaoImp implements V2DbMxOldSmsesDao {

	// 运营商的中间表:申请人基本信息表，用来获取该表ID传入V2_DB_MX_OLD_SMSES表中baseinfo_id字段参数
	public V2DbMxBase getV2DbMxBase(String cid) {
		if (cid == null) {
			return null;
		}
		String rowkey = new StringBuilder(cid).reverse().toString();
		HbaseBase<V2DbMxBase> base = new HbaseBase<V2DbMxBase>(new V2DbMxBase());
		return base.get("V2_DB_MX_BASE", rowkey, "smses");
	}

	// 获取语音详情
	public Map<String, Object> getV2DbMxOldSmses(String baseinfo_id) {
		if (baseinfo_id == null) {
			return null;
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMxOldSmses> base = new HbaseBase<V2DbMxOldSmses>(new V2DbMxOldSmses());
		return base.scan("V2_DB_MX_OLD_SMSES", rowkey, "smses", base);
	}

	public V2DbOperatorTask getV2DbOperatorTask(String cid) {
		if (cid == null) {
			return null;
		}
		String rowkey = new StringBuilder(cid).reverse().toString();
		HbaseBase<V2DbOperatorTask> base = new HbaseBase<V2DbOperatorTask>(new V2DbOperatorTask());
		return base.get("V2_DB_OPERATOR_TASK", rowkey, "c");
	}

	// 获取V2_DB_OPERATOR_SMS表信息
	public Map<String, Object> getV2DbOperatorSms(String task_id) {
		if (task_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(task_id).reverse().toString() + "|";
		HbaseBase<V2DbOperatorSms> base = new HbaseBase<V2DbOperatorSms>(new V2DbOperatorSms());
		return base.scan("V2_DB_OPERATOR_SMS", rowkey, "c", base);
	}
}
