package com.min.hbasedao.net;

import java.util.Map;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.V2DbXdBase;
import com.min.utils.HbaseUtils;

@Component
public class V2DbXdNetsDaoImp implements V2DbXdNetsDao {

	// 运营商C的中间表
	public Map<String, Object> getV2DbXdBase(String cid) {
		if (cid == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(cid).reverse().toString() + "|";
		HbaseBase<V2DbXdBase> base = new HbaseBase<V2DbXdBase>(new V2DbXdBase());
		return base.scan("V2_DB_XD_BASE", rowkey, "c", base);
	}

	// 获取上网记录
	public Map<String, Object> getV2DbXdNets(String baseinfo_id) {
		if (baseinfo_id == null) {
			return HbaseUtils.returnNull();
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbXdBase> base = new HbaseBase<V2DbXdBase>(new V2DbXdBase());
		return base.scan("V2_DB_XD_NETS", rowkey, "c", base);
	}
}
