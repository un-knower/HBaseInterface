package com.min.hbasedao.net;

import java.util.Map;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.call.V2DbMxBase;
import com.min.utils.HbaseUtils;

@Component
public class V2DbMxOldNetsDaoImpl implements V2DbMxOldNetsDao {

	// 运营商的中间表
	public Map<String, Object> getV2DbMxBase(String cid) {
		if (cid == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(cid).reverse().toString();
		HbaseBase<V2DbMxBase> base = new HbaseBase<V2DbMxBase>(new V2DbMxBase());
		return base.scan("V2_DB_MX_BASE", rowkey, "c", base);
	}

	// 获取语音详情
	public Map<String, Object> getV2DbMxOldNets(String baseinfo_id) {
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMxBase> base = new HbaseBase<V2DbMxBase>(new V2DbMxBase());
		return base.scan("V2_DB_MX_OLD_NETS", rowkey, "c", base);
	}
}
