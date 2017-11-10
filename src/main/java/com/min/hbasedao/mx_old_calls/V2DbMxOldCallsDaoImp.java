package com.min.hbasedao.mx_old_calls;

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

import com.min.model.mx_old_calls.V2DbMxBase;
import com.min.model.mx_old_calls.V2DbMxOldCalls;
import com.min.utils.HbaseUtils;

@Component
public class V2DbMxOldCallsDaoImp implements V2DbMxOldCallsDao {


	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
		
	// 运营商的中间表:申请人基本信息表，用来获取该表ID传入V2_DB_MX_OLD_CALLS表中baseinfo_id字段参数
	public V2DbMxBase getV2DbMxBase(String cid) {
		
		
					String rowkey = new StringBuilder(cid).reverse().toString();
					if (rowkey == null) {
						return null;
					}
					try {
						V2DbMxBase mx = new V2DbMxBase();
						Connection con = ConnectionFactory.createConnection(conf);
						Table table = con.getTable(TableName.valueOf("V2_DB_MX_BASE"));
						Result result = table.get(new Get(Bytes.toBytes(rowkey)));
						mx.setId(Bytes.toString(result.getValue(Bytes.toBytes("m"), Bytes.toBytes("ID"))));
						System.out.println("ID" + mx.getId());
						return mx;
					} catch (IOException e) {
						return null;
					}
	}
	
	//获取语音详情
	public List<V2DbMxOldCalls> getV2DbMxOldCalls(String baseinfo_id) {

		
		List<V2DbMxOldCalls> list = new ArrayList<V2DbMxOldCalls>();
				try {
					Connection con = ConnectionFactory.createConnection(conf);
					Table table = con.getTable(TableName.valueOf("V2_DB_MX_OLD_CALLS"));
					String colum = "calls";
					Scan scan = new Scan();
					String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
					System.out.println("rowkey" + rowkey);
					scan.setRowPrefixFilter(rowkey.getBytes());
					ResultScanner scanner = table.getScanner(scan);

					for (Result res : scanner) {
						V2DbMxOldCalls v2DbMxOldCalls = new V2DbMxOldCalls();
								@SuppressWarnings("unchecked")
								Class<V2DbMxOldCalls> cls = (Class<V2DbMxOldCalls>) v2DbMxOldCalls.getClass();
								Field[] fields = cls.getDeclaredFields();
								for (Field field : fields) {
									field.setAccessible(true);
									String fieldName = field.getName();
									field.set(v2DbMxOldCalls,
											Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase()))));
								}
								list.add(v2DbMxOldCalls);
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
