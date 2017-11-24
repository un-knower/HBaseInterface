package com.min.hbasedao.bill;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import com.min.model.V2DbXdTransactions;
import com.min.utils.HbaseUtils;

@Component
public class V2DbXdTransactionsDaoImp implements V2DbXdTransactionsDao {

	// 加载配置文件
		static Configuration conf = HBaseConfiguration.create();
		
		public List<V2DbXdTransactions> getContacts(String baseinfo_id) {

			List<V2DbXdTransactions> list = new ArrayList<V2DbXdTransactions>();
			try {
				Connection con = ConnectionFactory.createConnection(conf);
				Table table = con.getTable(TableName.valueOf("V2_DB_XD_TRANSACTIONS"));
				String colum = "c";
				Scan scan = new Scan();
				String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
				scan.setRowPrefixFilter(rowkey.getBytes());
				ResultScanner scanner = table.getScanner(scan);

				for (Result res : scanner) {
					V2DbXdTransactions v2DbXdTransactions = new V2DbXdTransactions();
					@SuppressWarnings("unchecked")
					Class<V2DbXdTransactions> cls = (Class<V2DbXdTransactions>) v2DbXdTransactions.getClass();
					Field[] fields = cls.getDeclaredFields();
					for (Field field : fields) {
						field.setAccessible(true);
						String fieldName = field.getName();
						field.set(v2DbXdTransactions, Bytes.toString(res.getValue(Bytes.toBytes(colum),
								Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase()))));
					}
					list.add(v2DbXdTransactions);
				}
				scanner.close();
				table.close();
				return list;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	
}
