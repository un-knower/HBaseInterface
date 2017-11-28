package com.min.hbasedao.call;

import java.util.Map;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.call.V2DbMxBase;
import com.min.model.call.V2DbMxOldCalls;
import com.min.utils.HbaseUtils;

@Component
public class V2DbMxOldCallsDaoImp implements V2DbMxOldCallsDao {

	// 运营商的中间表:申请人基本信息表，用来获取该表ID传入V2_DB_MX_OLD_CALLS表中baseinfo_id字段参数
	public V2DbMxBase getV2DbMxBase(String cid) {
		if (cid == null) {
			return null;
		}
		String rowkey = new StringBuilder(cid).reverse().toString();
		HbaseBase<V2DbMxBase> base = new HbaseBase<V2DbMxBase>(new V2DbMxBase());
		return base.get("V2_DB_MX_BASE", rowkey, "m");
	}

	// 获取语音详情
	public Map<String, Object> getV2DbMxOldCalls(String baseinfo_id) {
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMxOldCalls> base = new HbaseBase<V2DbMxOldCalls>(new V2DbMxOldCalls());
		return base.scan("V2_DB_MX_OLD_CALLS", rowkey, "calls", base);
	}
}
