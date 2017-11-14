package com.min.hbasedao.sms;

import java.util.List;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.call.V2DbMoBase;
import com.min.model.sms.V2DbMoRecordsSms;

@Component
public class V2DbMoRecordsSmsDaoImp implements V2DbMoRecordsSmsDao {

	// 运营商B短信表查询所需的中间表v2_db_mo_base，用来获取该表ID传入V2_DB_MO_RECORDS_SMS表中baseinfo_id字段参数
	public V2DbMoBase getV2DbMoBase(String cid) {
		if (cid == null) {
			return null;
		}
		String rowkey = new StringBuilder(cid).reverse().toString();
		HbaseBase<V2DbMoBase> base = new HbaseBase<V2DbMoBase>();
		return base.get("V2_DB_MO_BASE", rowkey, "mb");
	}

	// 获取运营商B短信详情
	public List<V2DbMoRecordsSms> getV2DbMoRecordsSms(String baseinfo_id) {
		if (baseinfo_id == null) {
			return null;
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMoRecordsSms> base = new HbaseBase<V2DbMoRecordsSms>();
		return base.scan("V2_DB_MO_RECORDS_SMS", rowkey, "sms");
	}
}
