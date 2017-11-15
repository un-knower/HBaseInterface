package com.min.hbasedao.bill;

import java.util.List;
import org.springframework.stereotype.Component;
import com.min.hbasedao.HbaseBase;
import com.min.model.V2DbXdTransactions;

@Component
public class V2DbXdTransactionsDaoImp implements V2DbXdTransactionsDao {

	public List<V2DbXdTransactions> getContacts(String cid) {
		if (cid == null) {
			return null;
		}
		HbaseBase<V2DbXdTransactions> base = new HbaseBase<V2DbXdTransactions>(new V2DbXdTransactions());
		return base.scan("V2_DB_XD_TRANSACTIONS", cid, "c");
	}
}
