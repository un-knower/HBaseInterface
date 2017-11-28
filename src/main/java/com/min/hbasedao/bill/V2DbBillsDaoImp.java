package com.min.hbasedao.bill;

import java.util.Map;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.V2DbXdTransactions;
import com.min.model.bill.V2DbMxOldBills;
import com.min.utils.HbaseUtils;

@Component
public class V2DbBillsDaoImp implements V2DbBillsDao {

	// 获取语音详情
	public Map<String, Object> getMxOldBills(String baseinfo_id) {
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMxOldBills> MxOldBills = new HbaseBase<V2DbMxOldBills>(new V2DbMxOldBills());
		return MxOldBills.scan("V2_DB_MX_OLD_BILLS", rowkey, "c", MxOldBills);
	}

	public Map<String, Object> getDbXdTransactions(String baseinfo_id) {
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbXdTransactions> base = new HbaseBase<V2DbXdTransactions>(new V2DbXdTransactions());
		return base.scan("V2_DB_XD_TRANSACTIONS", rowkey, "c", base);
	}
}
