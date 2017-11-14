package com.min.hbasedao.sms;

import java.util.List;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.sms.V2DbOperatorSms;
import com.min.model.sms.V2DbOperatorTask;

@Component
public class V2DbOperatorSmsDaoImp implements V2DbOperatorSmsDao {
	// 用来获取该表ID传入V2_DB_OPERATOR_SMS表中baseinfo_id字段参数
	public V2DbOperatorTask getV2DbOperatorTask(String cid) {
		if (cid == null) {
			return null;
		}
		String rowkey = new StringBuilder(cid).reverse().toString();
		HbaseBase<V2DbOperatorTask> base = new HbaseBase<V2DbOperatorTask>();
		return base.get("V2_DB_OPERATOR_TASK", rowkey, "ot");
	}

	// 获取V2_DB_OPERATOR_SMS表信息
	public List<V2DbOperatorSms> getV2DbOperatorSms(String task_id) {
		if (task_id == null) {
			return null;
		}
		String rowkey = new StringBuilder(task_id).reverse().toString() + "|";
		HbaseBase<V2DbOperatorSms> base = new HbaseBase<V2DbOperatorSms>();
		return base.scan("V2_DB_OPERATOR_SMS", rowkey, "sms");
	}
}
