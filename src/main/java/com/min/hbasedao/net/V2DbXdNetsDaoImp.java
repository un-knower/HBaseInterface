package com.min.hbasedao.net;

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
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import com.min.model.V2DbXdBase;
import com.min.model.net.V2DbXdNets;
import com.min.utils.HbaseUtils;

@Component
public class V2DbXdNetsDaoImp implements V2DbXdNetsDao {

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
	
	// 运营商C的中间表
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
					v2XB.setID(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("ID")))); // 按照需求只需要ID
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
	
	
	/*@SuppressWarnings("deprecation")
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
						System.out.println("scanner" + scanner.next().getColumn(Bytes.toBytes("nets"), Bytes.toBytes("BASEINFO_ID")));
						// 遍历结果
						for (Result res : scanner) {
							V2DbXdNets v2 = new V2DbXdNets();
							@SuppressWarnings("unchecked")
							Class<V2DbXdNets> cls = (Class<V2DbXdNets>) v2.getClass();
							Field[] fields = cls.getDeclaredFields();
							for (Field field : fields) {
								field.setAccessible(true);
								String fieldName = field.getName();
								System.out.println(fieldName);
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
						e.printStackTrace();
						return null;
					}
					System.out.println("list:" + list.size());
					return list;
				}*/
	
				public List<V2DbXdNets> getV2DbXdNets(String baseinfo_id) {
			
					List<V2DbXdNets> list = new ArrayList<V2DbXdNets>();
					try {
						Connection con = ConnectionFactory.createConnection(conf);
						Table table = con.getTable(TableName.valueOf("V2_DB_XD_NETS"));
						String colum = "smses";
						Scan scan = new Scan();
						String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
						System.out.println("rowkey" + rowkey);
						scan.setRowPrefixFilter(rowkey.getBytes());
						ResultScanner scanner = table.getScanner(scan);
			
						for (Result res : scanner) {
							V2DbXdNets v2DbXdNets = new V2DbXdNets();
							@SuppressWarnings("unchecked")
							Class<V2DbXdNets> cls = (Class<V2DbXdNets>) v2DbXdNets.getClass();
							Field[] fields = cls.getDeclaredFields();
							for (Field field : fields) {
								field.setAccessible(true);
								String fieldName = field.getName();
								field.set(v2DbXdNets, Bytes.toString(res.getValue(Bytes.toBytes(colum),
										Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase()))));
							}
							list.add(v2DbXdNets);
						}
						//System.out.println("listqqqqqqqwwwweee1234" + list.size());
						scanner.close();
						table.close();
						return list;
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
	
	
	}
