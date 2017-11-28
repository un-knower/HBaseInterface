package com.min.hbasedao.call;

import java.util.Map;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.V2DbOperatorTask;
import com.min.model.V2DbXdBase;
import com.min.model.V2ZScustomerInfo;
import com.min.model.call.V2DbContact;
import com.min.model.call.V2DbMoBase;
import com.min.model.call.V2DbMoRecordsCall;
import com.min.model.call.V2DbOperatorCall;
import com.min.model.call.V2DbXdCalls;
import com.min.utils.HbaseUtils;

@Component
public class V2DbCallDaoImpl implements V2DbCallDao {

	// 运营商B的通话记录表
	public Map<String, Object> getV2DbOperatorCall(String phoneid, int limit, String lastRowkey) {
		if (phoneid == null) {
			return null;
		}
		HbaseBase<V2DbOperatorCall> base = new HbaseBase<V2DbOperatorCall>(new V2DbOperatorCall());
		return base.scan("V2_DB_OPERATOR_CALL", phoneid + "|", "c", base);
	}

	public Map<String, Object> getContacts(String cid, int limit, String lastRowkey) {
		if (cid == null) {
			return null;
		}
		HbaseBase<V2DbContact> base = new HbaseBase<V2DbContact>(new V2DbContact());
		return base.scan("V2_DB_CONTACT", new StringBuilder(cid).reverse().toString() + "|", "con", base);
	}

	// 运营商A的中间表
	public V2DbMoBase getV2DbMoBase(String cid) {
		if (cid == null) {
			return null;
		}
		String rowkey = new StringBuilder(cid).reverse().toString();
		HbaseBase<V2DbMoBase> base = new HbaseBase<V2DbMoBase>(new V2DbMoBase());
		return base.get("V2_DB_MO_BASE", rowkey, "c");
	}

	// 运营商B的中间表
	public Map<String, Object> getOperatorTask(String cid, int limit, String lastRowkey) {
		if (cid == null) {
			return null;
		}
		String rowkey = new StringBuilder(cid).reverse().toString() + "|";
		HbaseBase<V2DbOperatorTask> base = new HbaseBase<V2DbOperatorTask>(new V2DbOperatorTask());
		return base.scan("V2_DB_OPERATOR_TASK", rowkey, "c", base);
	}

	// 运营商C的中间表
	public Map<String, Object> getV2DbXdBase(String cid, int limit, String lastRowkey) {
		if (cid == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(cid).reverse().toString() + "|";
		HbaseBase<V2DbXdBase> base = new HbaseBase<V2DbXdBase>(new V2DbXdBase());
		return base.scan("V2_DB_XD_BASE", rowkey, "c", base);
	}

	public Map<String, Object> getV2DbXdCalls(String baseinfo_id, int limit, String lastRowkey) {
		// TODO Auto-generated method stub
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbXdCalls> base = new HbaseBase<V2DbXdCalls>(new V2DbXdCalls());
		return base.scan("V2_DB_XD_CALLS", rowkey, "c", base);
	}

	public Map<String, Object> getV2DbMoRecordsCall(String baseInfoId, int limit, String lastRowkey) {
		if (baseInfoId == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseInfoId).reverse().toString() + "|";
		HbaseBase<V2DbMoRecordsCall> base = new HbaseBase<V2DbMoRecordsCall>(new V2DbMoRecordsCall());
		return base.scan("V2_DB_MO_RECORDS_CALL", rowkey, "c", base);
	}

	public V2ZScustomerInfo getCustomr(String idcard, String siteid, String mobile) {
		// TODO Auto-generated method stub
		return HbaseBase.getCustomerInfo(idcard, siteid, mobile);
	}
}
