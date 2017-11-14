package com.min.hbasedao.sms;

import java.util.List;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.call.V2DbMxBase;
import com.min.model.sms.V2DbMxOldSmses;

@Component
public class V2DbMxOldSmsesDaoImp implements V2DbMxOldSmsesDao {

	// 运营商的中间表:申请人基本信息表，用来获取该表ID传入V2_DB_MX_OLD_SMSES表中baseinfo_id字段参数
	public V2DbMxBase getV2DbMxBase(String cid) {
		if (cid == null) {
			return null;
		}
		String rowkey = new StringBuilder(cid).reverse().toString();
		HbaseBase<V2DbMxBase> base = new HbaseBase<V2DbMxBase>();
		return base.get("V2_DB_MX_BASE", rowkey, "m");
	}

	// 获取语音详情
	public List<V2DbMxOldSmses> getV2DbMxOldSmses(String baseinfo_id) {
		if (baseinfo_id == null) {
			return null;
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMxOldSmses> base = new HbaseBase<V2DbMxOldSmses>();
		return base.scan("V2_DB_MX_OLD_SMSES", rowkey, "smses");
	}
}
