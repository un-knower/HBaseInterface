package com.min.hbasedao.bill;

import java.util.Map;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.bill.V2DbMoRecordsBill;
import com.min.model.bill.V2DbMxOldBills;
import com.min.model.bill.V2DbOperatorBill;
import com.min.model.bill.V2DbXdTransactions;
import com.min.utils.HbaseUtils;

@Component
public class V2DbBillsDaoImp implements V2DbBillsDao {

	// 获取语音详情
	public Map<String, Object> getMxOldBills(String baseinfo_id, int limit, String lastRowkey) {
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMxOldBills> base = new HbaseBase<V2DbMxOldBills>(new V2DbMxOldBills());
		return base.scan("V2_DB_MX_OLD_BILLS", rowkey, "c", base);
	}

	public Map<String, Object> getDbXdTransactions(String baseinfo_id, int limit, String lastRowkey) {
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbXdTransactions> base = new HbaseBase<V2DbXdTransactions>(new V2DbXdTransactions());
		return base.scan("V2_DB_XD_TRANSACTIONS", rowkey, "c", base);
	}

	public Map<String, Object> getMoRecordsBill(String baseinfo_id, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMoRecordsBill> base = new HbaseBase<V2DbMoRecordsBill>(new V2DbMoRecordsBill());
		return base.scan("V2_DB_MO_RECORDS_BILL", rowkey, "c", base);
	}

	public Map<String, Object> getOperatorBill(String phoneid, int limit, String lastRowKey) {
		if (phoneid == null) {
			return HbaseUtils.returnNull();
		}
		HbaseBase<V2DbOperatorBill> base = new HbaseBase<V2DbOperatorBill>(new V2DbOperatorBill());
		return base.scan("V2_DB_OPERATOR_BILL", phoneid+"|", "c", base);
	}
}
