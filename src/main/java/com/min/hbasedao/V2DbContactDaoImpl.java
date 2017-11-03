package com.min.hbasedao;

import java.io.IOException;
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
import org.apache.hadoop.hbase.filter.BinaryPrefixComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import com.min.model.V2DbContact;
@Component
public class V2DbContactDaoImpl implements V2DbContactDao {
	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
	// hbase表名
	static String tableName = "V2_DB_CONTACT";

	public List<V2DbContact> getContacts(String cid) {
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
				V2DbContact v2Con = new V2DbContact();
				v2Con.setId(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ID"))));
				v2Con.setCid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CID"))));
				v2Con.setMobile(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("MOBILE"))));
				v2Con.setAddtime(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ADDTIME"))));
				v2Con.setName(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("NAME"))));
				v2Con.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("USERID"))));
				list.add(v2Con);
			}
			scanner.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	//测试
//	public static void main(String[] args) {
//		V2DbContactDaoImpl dao = new V2DbContactDaoImpl();
//		System.out.println(dao.getContacts("2467").size());
//	}
}
