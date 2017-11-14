package com.min.hbasedao.sms;

import java.util.List;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.V2DbXdBase;
import com.min.model.sms.V2DbXdSmses;

@Component
public class V2DbXdSmsesDaoImp implements V2DbXdSmsesDao {

	public V2DbXdBase getV2DbXdBase(String cid) {
		if (cid == null) {
			return null;
		}
		String rowkey = new StringBuilder(cid).reverse().toString() + "|";
		HbaseBase<V2DbXdBase> base = new HbaseBase<V2DbXdBase>();
		return base.get("V2_DB_XD_BASE", rowkey, "xb");
	}

	public List<V2DbXdSmses> getV2DbXdSmses(String baseinfo_id) {
		if (baseinfo_id == null) {
			return null;
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbXdSmses> base = new HbaseBase<V2DbXdSmses>();
		return base.scan("V2_DB_XD_SMSES", rowkey, "smses");
	}
}
