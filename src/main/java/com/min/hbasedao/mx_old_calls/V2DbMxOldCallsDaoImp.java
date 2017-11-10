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

import com.min.model.V2DbXdTransactions;
import com.min.model.mx_old_calls.V2DbMxBase;
import com.min.model.mx_old_calls.V2DbMxOldCalls;

@Component
public class V2DbMxOldCallsDaoImp implements V2DbMxOldCallsDao {


	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
		
	// 运营商的中间表:申请人基本信息表，用来获取该表ID传入V2_DB_MX_OLD_CALLS表中baseinfo_id字段参数
	public V2DbMxBase getV2DbMxBase(String cid, String addtime) {
		
		
		// TODO Auto-generated method stub
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
	public List<V2DbMxOldCalls> getV2DbMxOldCalls(String baseinfo_id, String addtime) {

		V2DbMxOldCalls v2MxOldC = new V2DbMxOldCalls();		
		
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
					
					
					// 遍历结果
					for (Result res : scanner) {
						//获取数据库中的时间戳
						String time = Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("ADDTIME")));
						//将时间戳转化为long型
						long timelong = Long.parseLong(time);
						System.out.println("timelong" + timelong);
						if (addtime != null && addtime.length() > 0) {
							//获取当月时间戳
							long addT = new java.text.SimpleDateFormat("yyyyMM").parse(addtime).getTime() / 1000;
							//查看数据库中的时间戳是否在用户填写的参数时间范围内
							if (timelong >= addT && timelong <= (addT + 3600 * 30 * 24)) { 
							
								// 保存到实体类
								//V2DbMxOldCalls v2MxOldC = new V2DbMxOldCalls();
							
								v2MxOldC.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("USERID"))));
								v2MxOldC.setCid(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("CID"))));
								v2MxOldC.setAddtime(String.valueOf(time));
								v2MxOldC.setBaseinfoId(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("BASEINFO_ID"))));
								v2MxOldC.setBillMonth(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("BILL_MONTH"))));
								v2MxOldC.setTotalSize(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("TOTAL_SIZE"))));
								v2MxOldC.setTime(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("TIME"))));
								v2MxOldC.setLocation(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("LOCATION"))));
								v2MxOldC.setFee(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("FEE"))));
								v2MxOldC.setDetailsId(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("DETAILS_ID"))));
								v2MxOldC.setPeerNumber(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("PEER_NUMBER"))));
								v2MxOldC.setLocationType(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("LOCATION_TYPE"))));
								v2MxOldC.setDuration(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("DURATION"))));
								v2MxOldC.setDialType(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("DIAL_TYPE"))));

							list.add(v2MxOldC);
							 } 
						} else {
							
								// 保存到实体类
								//V2DbMxOldCalls v2MxOldC = new V2DbMxOldCalls();
							
								v2MxOldC.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("USERID"))));
								v2MxOldC.setCid(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("CID"))));
								v2MxOldC.setAddtime(String.valueOf(time));
								v2MxOldC.setBaseinfoId(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("BASEINFO_ID"))));
								v2MxOldC.setBillMonth(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("BILL_MONTH"))));
								v2MxOldC.setTotalSize(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("TOTAL_SIZE"))));
								v2MxOldC.setTime(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("TIME"))));
								v2MxOldC.setLocation(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("LOCATION"))));
								v2MxOldC.setFee(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("FEE"))));
								v2MxOldC.setDetailsId(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("DETAILS_ID"))));
								v2MxOldC.setPeerNumber(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("PEER_NUMBER"))));
								v2MxOldC.setLocationType(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("LOCATION_TYPE"))));
								v2MxOldC.setDuration(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("DURATION"))));
								v2MxOldC.setDialType(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("DIAL_TYPE"))));
	
							list.add(v2MxOldC);
						}
						
						v2MxOldC.getAddtime();
					}
					scanner.close();
					

					/*for (Result res : scanner) {
						V2DbMxOldCalls v2DbMxOldCalls = new V2DbMxOldCalls();
						long time = Bytes.toLong((res.getValue(Bytes.toBytes(colum), Bytes.toBytes("ADDTIME"))));
						if (addtime != null && addtime.length() > 0) {
							long addT = new java.text.SimpleDateFormat("yyyyMM").parse(addtime).getTime() / 1000;
							if (time >= addT && time <= (addT + 3600 * 30 * 24)) {
								@SuppressWarnings("unchecked")
								Class<V2DbMxOldCalls> cls = (Class<V2DbMxOldCalls>) v2DbMxOldCalls.getClass();
								Field[] fields = cls.getDeclaredFields();
								for (Field field : fields) {
									field.setAccessible(true);
									String fieldName = field.getName();
									field.set(v2DbMxOldCalls,
											Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes(fieldName))));
								}
								list.add(v2DbMxOldCalls);
							}
						} else {
							@SuppressWarnings("unchecked")
							Class<V2DbMxOldCalls> cls = (Class<V2DbMxOldCalls>) v2DbMxOldCalls.getClass();
							Field[] fields = cls.getDeclaredFields();
							for (Field field : fields) {
								field.setAccessible(true);
								String fieldName = field.getName();
								field.set(v2DbMxOldCalls,
										Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes(fieldName))));
							}
							list.add(v2DbMxOldCalls);
							
						}
					}
					System.out.println(list.size());
					
					table.close();
					scanner.close();*/
					
					System.out.println(v2MxOldC.getAddtime() + v2MxOldC.getBaseinfoId());
					return list;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				}
				
	}

	
	public static void main(String[] args) {
		
		V2DbMxOldCallsDaoImp ii = new V2DbMxOldCallsDaoImp();
		//ii.getV2DbMxBase("13072000", "1506142823");
		//ID270388
		ii.getV2DbMxOldCalls("10000", "1498090940");
	}
	
}
