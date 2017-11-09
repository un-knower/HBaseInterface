package com.min.hbasedao.net;

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
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;
import com.min.model.net.V2DbMxNet;
import com.min.utils.HbaseUtils;

@Component
public class V2DBNetsDaoImpl implements V2DBNetsDao {
	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();

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
			// 根据rowkey 后缀过滤查找结果
			String rowkey = ".*" + cid;
			Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(rowkey));
			scan.setFilter(filter);
			ResultScanner scanner = table.getScanner(scan);
			// 遍历结果
			for (Result res : scanner) {
				// System.out.println(res);
				// 保存到实体类
				long time = Bytes.toLong((res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ADDTIME"))));
				if (addTime != null && addTime.length() > 0) {
					long addT = new java.text.SimpleDateFormat("yyyyMM").parse(addTime).getTime() / 1000;
					if (time >= addT && time <= (addT + 3600 * 30 * 24)) {
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
}
