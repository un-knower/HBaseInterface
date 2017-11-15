package com.min.hbasedao.bill;

import java.util.List;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.V2DbMxOldBills;

@Component
public class V2DbMxOldBillsDaoImp implements V2DbMxOldBillsDao {

	public List<V2DbMxOldBills> getContacts(String cid) {
		if (cid == null) {
			return null;
		}
		HbaseBase<V2DbMxOldBills> base = new HbaseBase<V2DbMxOldBills>(new V2DbMxOldBills());
		return base.scan("V2_DB_MX_OLD_BILLS", cid, "bills");
	}
}
