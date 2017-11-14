package com.min.hbasedao.call;

import java.util.List;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.call.V2DbMxBase;
import com.min.model.call.V2DbMxOldCalls;

@Component
public class V2DbMxOldCallsDaoImp implements V2DbMxOldCallsDao {

	// 运营商的中间表:申请人基本信息表，用来获取该表ID传入V2_DB_MX_OLD_CALLS表中baseinfo_id字段参数
	public V2DbMxBase getV2DbMxBase(String cid) {
		if (cid == null) {
			return null;
		}
		String rowkey = new StringBuilder(cid).reverse().toString();
		HbaseBase<V2DbMxBase> base = new HbaseBase<V2DbMxBase>();
		return base.get("V2_DB_MX_BASE", rowkey, "m");
	}

	// 获取语音详情
	public List<V2DbMxOldCalls> getV2DbMxOldCalls(String baseinfo_id) {
		if (baseinfo_id == null) {
			return null;
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMxOldCalls> base = new HbaseBase<V2DbMxOldCalls>();
		return base.scan("V2_DB_MX_OLD_CALLS", rowkey, "calls");
	}
}
