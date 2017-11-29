package com.min.hbasedao.net;

import java.util.Map;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.base.V2DbXdBase;
import com.min.model.net.V2DbMxOldNets;
import com.min.utils.HbaseUtils;

@Component
public class V2DbNetsDaoImp implements V2DbNetsDao {

	// 获取上网记录
	public Map<String, Object> getV2DbXdNets(String baseinfo_id, int limit, String lastRowkeys) {
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbXdBase> base = new HbaseBase<V2DbXdBase>(new V2DbXdBase());
		return base.scan("V2_DB_XD_NETS", rowkey, "c", base);
	}

	// 获取语音详情
	public Map<String, Object> getV2DbMxOldNets(String baseinfo_id, int limit, String lastRowkey) {
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMxOldNets> base = new HbaseBase<V2DbMxOldNets>(new V2DbMxOldNets());
		return base.scan("V2_DB_MX_OLD_NETS", rowkey, "c", base);
	}
}
