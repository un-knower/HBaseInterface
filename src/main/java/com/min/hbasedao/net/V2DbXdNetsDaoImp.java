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

import com.min.model.net.V2DbXdNets;
import com.min.utils.HbaseUtils;

@Component
public class V2DbXdNetsDaoImp implements V2DbXdNetsDao {

	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
	
	public List<V2DbXdNets> getV2DbXdNets(String baseinfo_id) {

		// TODO Auto-generated method stub
					List<V2DbXdNets> list = new ArrayList<V2DbXdNets>();
					try {
						// 根据配置得到连接
						Connection con = ConnectionFactory.createConnection(conf);
						// 根据连接得到表
						Table table = con.getTable(TableName.valueOf("V2_DB_XD_NETS"));
						String cloum = "nets";
						Scan scan = new Scan();
						// 根据rowkey 后缀过滤查找结果
						String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
						System.out.println("rowkey:" + rowkey);
						Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL, new RegexStringComparator(rowkey));
						scan.setFilter(filter);
						ResultScanner scanner = table.getScanner(scan);
						//System.out.println("scanner" + scanner.next().getColumn(Bytes.toBytes("nets"), Bytes.toBytes("BASEINFO_ID")));
						// 遍历结果
						for (Result res : scanner) {
							V2DbXdNets v2 = new V2DbXdNets();
							@SuppressWarnings("unchecked")
							Class<V2DbXdNets> cls = (Class<V2DbXdNets>) v2.getClass();
							Field[] fields = cls.getDeclaredFields();
							for (Field field : fields) {
								field.setAccessible(true);
								String fieldName = field.getName();
								field.set(v2, res.getValue(Bytes.toBytes(cloum),
										Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase())));
							}
							list.add(v2);
						}
						
						scanner.close();
						table.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						System.out.println("list2:" + list.size());
						return null;
					}
					System.out.println("list:" + list.size());
					return list;
				}
	public static void main(String[] args) {
		V2DbXdNetsDaoImp pp = new V2DbXdNetsDaoImp();
		System.out.println("pp.getV2DbXdNets(\"2000\");" + pp.getV2DbXdNets("1000"));
	}
	
	}
