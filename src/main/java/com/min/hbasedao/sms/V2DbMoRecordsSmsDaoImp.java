package com.min.hbasedao.sms;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import com.min.model.call.V2DbMoBase;
import com.min.model.sms.V2DbMoRecordsSms;
import com.min.utils.HbaseUtils;

@Component
public class V2DbMoRecordsSmsDaoImp implements V2DbMoRecordsSmsDao {

	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
	
	// 运营商B短信表查询所需的中间表v2_db_mo_base，用来获取该表ID传入V2_DB_MO_RECORDS_SMS表中baseinfo_id字段参数
	public V2DbMoBase getV2DbMoBase(String cid) {
		try {
			String rowkey = new StringBuilder(cid).reverse().toString();
			if (rowkey == null) {
				return null;
			}

			V2DbMoBase mo = new V2DbMoBase();
			Connection con;

			con = ConnectionFactory.createConnection(conf);
			Table table = con.getTable(TableName.valueOf("V2_DB_MO_BASE"));
			Result result = table.get(new Get(Bytes.toBytes(rowkey)));
			mo.setId(Bytes.toString(result.getValue(Bytes.toBytes("mb"), Bytes.toBytes("ID"))));
			return mo;
		} catch (IOException e) {
			return null;
		}
	}
	
	// 获取运营商B短信详情
		public List<V2DbMoRecordsSms> getV2DbMoRecordsSms(String baseinfo_id) {

			List<V2DbMoRecordsSms> list = new ArrayList<V2DbMoRecordsSms>();
			try {
				Connection con = ConnectionFactory.createConnection(conf);
				Table table = con.getTable(TableName.valueOf("V2_DB_MO_RECORDS_SMS"));
				String colum = "sms";
				Scan scan = new Scan();
				String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
				scan.setRowPrefixFilter(rowkey.getBytes());
				ResultScanner scanner = table.getScanner(scan);

				for (Result res : scanner) {
					V2DbMoRecordsSms v2DbMoRecordsSms = new V2DbMoRecordsSms();
					@SuppressWarnings("unchecked")
					Class<V2DbMoRecordsSms> cls = (Class<V2DbMoRecordsSms>) v2DbMoRecordsSms.getClass();
					Field[] fields = cls.getDeclaredFields();
					for (Field field : fields) {
						field.setAccessible(true);
						String fieldName = field.getName();
						field.set(v2DbMoRecordsSms, Bytes.toString(res.getValue(Bytes.toBytes(colum),
								Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase()))));
					}
					list.add(v2DbMoRecordsSms);
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
