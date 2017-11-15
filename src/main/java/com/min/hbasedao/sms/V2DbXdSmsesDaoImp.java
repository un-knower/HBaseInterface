package com.min.hbasedao.sms;

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

import com.min.model.sms.V2DbXdSmses;
import com.min.utils.HbaseUtils;

@Component
public class V2DbXdSmsesDaoImp implements V2DbXdSmsesDao {

	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
	
	public List<V2DbXdSmses> getV2DbXdSmses(String baseinfo_id) {

		List<V2DbXdSmses> list = new ArrayList<V2DbXdSmses>();
		try {
			Connection con = ConnectionFactory.createConnection(conf);
			Table table = con.getTable(TableName.valueOf("V2_DB_XD_SMSES"));
			String colum = "smses";
			Scan scan = new Scan();
			String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
			System.out.println("rowkey" + rowkey);
			scan.setRowPrefixFilter(rowkey.getBytes());
			ResultScanner scanner = table.getScanner(scan);

			for (Result res : scanner) {
				V2DbXdSmses v2DbXdSmses = new V2DbXdSmses();
				@SuppressWarnings("unchecked")
				Class<V2DbXdSmses> cls = (Class<V2DbXdSmses>) v2DbXdSmses.getClass();
				Field[] fields = cls.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					field.set(v2DbXdSmses, Bytes.toString(res.getValue(Bytes.toBytes(colum),
							Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase()))));
				}
				list.add(v2DbXdSmses);
			}
			System.out.println("list" + list.size());
			scanner.close();
			table.close();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
