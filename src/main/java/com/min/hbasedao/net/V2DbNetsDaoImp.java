package com.min.hbasedao.net;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.min.hbasedao.HbaseBase;
import com.min.model.base.V2DbMxBase;
import com.min.model.base.V2DbXdBase;

@Component
public class V2DbNetsDaoImp implements V2DbNetsDao {

	// 获取上网记录
	public Map<String, Object> getV2DbXdNets(String baseinfo_id, int limit, String lastRowkeys) {
		if (baseinfo_id == null) {
			return null;
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbXdBase> base = new HbaseBase<V2DbXdBase>(new V2DbXdBase());
		return base.scan("V2_DB_XD_NETS", rowkey, "c", base);
	}

	// 获取语音详情
	public Map<String, Object> getV2DbMxOldNets(String baseinfo_id, int limit, String lastRowkey) {
		if (baseinfo_id == null) {
			return null;
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMxBase> base = new HbaseBase<V2DbMxBase>(new V2DbMxBase());
		return base.scan("V2_DB_MX_OLD_NETS", rowkey, "c", base);
	}
}
