package com.min.hbasedao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
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
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;
import com.min.model.V2DbContact;
import com.min.model.V2DbMxNet;
import com.min.model.V2ZScustomerInfo;
import com.min.utils.HbaseUtils;

@Component
public class V2DbContactDaoImpl implements V2DbContactDao {
	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
	// hbase表名
	static String tableName = "V2_DB_CONTACT";

	public List<V2DbContact> getContacts(String cid, String addTime) {
		// TODO Auto-generated method stub
		List<V2DbContact> list = new ArrayList<V2DbContact>();
		try {
			// 根据配置得到连接
			Connection con = ConnectionFactory.createConnection(conf);
			// 根据连接得到表
			Table table = con.getTable(TableName.valueOf(tableName));
			String cloum = "con";
			Scan scan = new Scan();

			// 根据rowkey过滤查找结果
			Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,
					// 以前缀开始
					new BinaryPrefixComparator(Bytes.toBytes(cid + "|")));
			scan.setFilter(filter);
			ResultScanner scanner = table.getScanner(scan);
			// 遍历结果
			for (Result res : scanner) {
				// System.out.println(res);
				// 保存到实体类
				long time = Bytes.toLong((res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ADDTIME"))));
				if (addTime != null && addTime.length() > 0) {
					long addT = new java.text.SimpleDateFormat("yyyyMM").parse(addTime).getTime() / 1000;
					if (time >= addT && time <= addT * 3600 * 30 * 24) {
						V2DbContact v2Con = new V2DbContact();
						v2Con.setId(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ID"))));
						v2Con.setCid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CID"))));
						v2Con.setMobile(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("MOBILE"))));
						v2Con.setAddtime(String.valueOf(time));
						v2Con.setName(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("NAME"))));
						v2Con.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("USERID"))));
						list.add(v2Con);
					}
				} else {
					V2DbContact v2Con = new V2DbContact();
					v2Con.setId(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ID"))));
					v2Con.setCid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CID"))));
					v2Con.setMobile(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("MOBILE"))));
					v2Con.setAddtime(String.valueOf(time));
					v2Con.setName(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("NAME"))));
					v2Con.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("USERID"))));
					list.add(v2Con);
				}
			}
			scanner.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<V2DbMxNet> getMxOldNets(String cid, String addTime) {
		// TODO Auto-generated method stub
		List<V2DbMxNet> list = new ArrayList<V2DbMxNet>();
		try {
			// 根据配置得到连接
			Connection con = ConnectionFactory.createConnection(conf);
			// 根据连接得到表
			Table table = con.getTable(TableName.valueOf("V2_DB_MX_OLD_NETS"));
			String cloum = "nets";
			Scan scan = new Scan();

			// 根据rowkey过滤查找结果
			Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,
					// 以前缀开始
					new BinaryComparator(Bytes.toBytes(cid)));
			scan.setFilter(filter);
			ResultScanner scanner = table.getScanner(scan);
			// 遍历结果
			for (Result res : scanner) {
				// System.out.println(res);
				// 保存到实体类
				long time = Bytes.toLong((res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ADDTIME"))));
				if (addTime != null && addTime.length() > 0) {
					long addT = new java.text.SimpleDateFormat("yyyyMM").parse(addTime).getTime() / 1000;
					if (time >= addT && time <= addT * 3600 * 30 * 24) {
						V2DbMxNet v2 = new V2DbMxNet();
						@SuppressWarnings("unchecked")
						Class<V2DbMxNet> cls = (Class<V2DbMxNet>) v2.getClass();
						Field[] fields = cls.getDeclaredFields();
						for (Field field : fields) {
							field.setAccessible(true);
							String fieldName = field.getName();
							field.set(v2, res.getValue(Bytes.toBytes(cloum),
									Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase())));
						}
						list.add(v2);
					}
				} else {
					V2DbMxNet v2 = new V2DbMxNet();
					@SuppressWarnings("unchecked")
					Class<V2DbMxNet> cls = (Class<V2DbMxNet>) v2.getClass();
					Field[] fields = cls.getDeclaredFields();
					for (Field field : fields) {
						field.setAccessible(true);
						String fieldName = field.getName();
						field.set(v2, res.getValue(Bytes.toBytes(cloum),
								Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase())));
					}
					list.add(v2);
				}
			}
			scanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return list;
		}
		return list;
	}

	public V2ZScustomerInfo getCustomr(String idcard, String siteid) {
		// TODO Auto-generated method stub
		String rowkey = HbaseUtils.transformRowkey(idcard, siteid);
		if (rowkey == null) {
			return null;
		}
		try {
			V2ZScustomerInfo info = new V2ZScustomerInfo();
			Connection con = ConnectionFactory.createConnection(conf);
			Table table = con.getTable(TableName.valueOf("V2_DB_CUSTOMER_INFO"));
			Result result = table.get(new Get(Bytes.toBytes(rowkey)));

			info.setId(String.valueOf(result.getValue(Bytes.toBytes("ic"), Bytes.toBytes("ID"))));
			return info;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	// 测试
	// public static void main(String[] args) throws ParseException {
	// V2DbContactDaoImpl dao = new V2DbContactDaoImpl();
	// System.out.println(dao.getContacts("2467").size());
	// }
}
