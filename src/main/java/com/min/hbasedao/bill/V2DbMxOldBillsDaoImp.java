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
import com.min.hbasedao.HbaseBase;
import com.min.model.V2DbMxOldBills;
import com.min.model.net.V2DbMxOldNets;
import com.min.utils.HbaseUtils;

@Component
public class V2DbMxOldBillsDaoImp implements V2DbMxOldBillsDao {

	/*public List<V2DbMxOldBills> getContacts(String cid) {
		if (cid == null) {
			return null;
		}
		HbaseBase<V2DbMxOldBills> base = new HbaseBase<V2DbMxOldBills>(new V2DbMxOldBills());
		return base.scan("V2_DB_MX_OLD_BILLS", cid, "bills");
	}*/
	
	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
	
	// 获取语音详情
			public List<V2DbMxOldBills> getContacts(String baseinfo_id) {

				List<V2DbMxOldBills> list = new ArrayList<V2DbMxOldBills>();
				try {
					Connection con = ConnectionFactory.createConnection(conf);
					Table table = con.getTable(TableName.valueOf("V2_DB_MX_OLD_BILLS"));
					String colum = "bills";
					Scan scan = new Scan();
					String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
					scan.setRowPrefixFilter(rowkey.getBytes());
					ResultScanner scanner = table.getScanner(scan);

					for (Result res : scanner) {
						V2DbMxOldBills v2DbMxOldBills = new V2DbMxOldBills();
						@SuppressWarnings("unchecked")
						Class<V2DbMxOldBills> cls = (Class<V2DbMxOldBills>) v2DbMxOldBills.getClass();
						Field[] fields = cls.getDeclaredFields();
						for (Field field : fields) {
							field.setAccessible(true);
							String fieldName = field.getName();
							field.set(v2DbMxOldBills, Bytes.toString(res.getValue(Bytes.toBytes(colum),
									Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase()))));
						}
						list.add(v2DbMxOldBills);
					}

					scanner.close();
					table.close();
					System.out.println("list" + list.size());
					return list;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}

			}
}
