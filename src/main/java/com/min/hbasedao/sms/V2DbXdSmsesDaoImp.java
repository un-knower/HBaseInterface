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
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import com.min.model.sms.V2DbXdBase;
import com.min.model.sms.V2DbXdSmses;
import com.min.utils.HbaseUtils;

@Component
public class V2DbXdSmsesDaoImp implements V2DbXdSmsesDao {

	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
	
	// 根据配置拿到连接
		static Connection con;
		static {
			try {
				con = ConnectionFactory.createConnection(conf);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public List<V2DbXdBase> getV2DbXdBase(String cid) {
		// TODO Auto-generated method stub
		List<V2DbXdBase> list = new ArrayList<V2DbXdBase>();
		try {
			Table table = con.getTable(TableName.valueOf("V2_DB_XD_BASE"));
			String colum = "xb";// 列族
			Scan scan = new Scan();
			// rowkey设计,反转cid
			String rowkey = new StringBuilder(cid).reverse().toString() + "|";
			// 根据前缀
			scan.setRowPrefixFilter(rowkey.getBytes());
			ResultScanner scanner = table.getScanner(scan);
			// 遍历结果

			for (Result res : scanner) {
				V2DbXdBase v2XB = new V2DbXdBase();
				v2XB.setId(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("ID")))); // 按照需求只需要ID
				list.add(v2XB);
			}
			scanner.close();
			table.close();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
		return list;
	}
	
	/*@SuppressWarnings("unused")
	public V2DbXdBase getV2DbXdBase(String cid) {
		
		String rowkey = new StringBuilder(cid).reverse().toString() + "|";
		System.out.println("rowkey" + rowkey);
		if (rowkey == null) {
			return null;
		}
		try {
			V2DbXdBase xb = new V2DbXdBase();
			Connection con = ConnectionFactory.createConnection(conf);
			Table table = con.getTable(TableName.valueOf("V2_DB_XD_BASE"));
			//Result result = table.get(new Get(Bytes.toBytes(rowkey)));	
			
			System.out.println("result" + result);
			xb.setId(Bytes.toString(result.getValue(Bytes.toBytes("xb"), Bytes.toBytes("ID"))));
			System.out.println("xb" + xb.getId() + "cid:" + Bytes.toString(result.getValue(Bytes.toBytes("xb"), Bytes.toBytes("ID"))));
			
			Scan scan = new Scan();
			System.out.println("rowkey" + rowkey);
			scan.setRowPrefixFilter(rowkey.getBytes());
			ResultScanner scanner = table.getScanner(scan);
			
			for (Result res : scanner) {
							String fieldName = field.getName();
							field.set(xb,
									Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes())));
						}
						list.add(v2DbXdSmses);
					}
			
			
			return xb;
		} catch (IOException e) {
			return null;
		}
	}*/

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
							field.set(v2DbXdSmses,
									Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase()))));
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

	
	public static void main(String[] args) {
		V2DbXdSmsesDaoImp ww = new V2DbXdSmsesDaoImp();
		ww.getV2DbXdSmses("2000");
	}
}
