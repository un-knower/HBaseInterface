package com.min.hbasedao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

import com.min.model.V2DbMxOldBills;

@Component
public class V2DbMxOldBillsDaoImp implements V2DbMxOldBillsDao {

			// 加载配置文件
			static Configuration conf = HBaseConfiguration.create();
			// hbase表名
			static String tableName = "V2_DB_MX_OLD_BILLS";

			public List<V2DbMxOldBills> getContacts(String cid) {
				List<V2DbMxOldBills> list = new ArrayList<V2DbMxOldBills>();
				try {
					// 根据配置得到连接
					Connection con = ConnectionFactory.createConnection(conf);
					// 根据连接得到表
					Table table = con.getTable(TableName.valueOf(tableName));
					String cloum = "bills";
					Scan scan = new Scan();
					// 根据rowkey过滤查找结果
					Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,
							// 以前缀开始
							new BinaryPrefixComparator(Bytes.toBytes(cid)));
					scan.setFilter(filter);
					ResultScanner scanner = table.getScanner(scan);
					// 遍历结果
					for (Result res : scanner) {
						// 保存到实体类
						V2DbMxOldBills v2MxOldB = new V2DbMxOldBills();
						
						/*Class<V2DbMxOldBills> cls = (Class<V2DbMxOldBills>) v2MxOldB.getClass();
						Field[] fields = cls.getFields();
						for (Field field : fields) {
							field.setAccessible(true);
							String filedName =field.getName();
							field.set(v2MxOldB, Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes(switchParam(filedName).toUpperCase()))));
						}*/
						
						v2MxOldB.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("USERID"))));
						v2MxOldB.setCid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CID"))));
						v2MxOldB.setAddtime(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ADDTIME"))));
						v2MxOldB.setBaseinfoId(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("BASEINFO_ID"))));
						v2MxOldB.setPoint(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("POINT"))));
						v2MxOldB.setBillMonth(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("BILL_MONTH"))));
						v2MxOldB.setBillStartDate(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("BILL_START_DATE"))));
						v2MxOldB.setBillEndDate(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("BILL_END_DATE"))));
						v2MxOldB.setBaseFee(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("BASE_FEE"))));
						v2MxOldB.setExtraServiceFee(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("EXTRA_SERVICE_FEE"))));
						v2MxOldB.setVoiceFee(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("VOICE_FEE"))));
						v2MxOldB.setSmsFee(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("SMS_FEE"))));
						v2MxOldB.setWebFee(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("WEB_FEE"))));
						v2MxOldB.setExtraFee(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("EXTRA_FEE"))));
						v2MxOldB.setTotalFee(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("TOTAL_FEE"))));
						v2MxOldB.setDiscount(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("DISCOUNT"))));
						v2MxOldB.setExtraDiscount(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("EXTRA_DISCOUNT"))));
						v2MxOldB.setActualfee(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ACTUALFEE"))));
						v2MxOldB.setPaidFee(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("PAID_FEE"))));
						v2MxOldB.setUnpaidFee(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("UNPAID_FEE"))));
						v2MxOldB.setLastPoint(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("LAST_POINT"))));
						v2MxOldB.setRelatedMobiles(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("RELATED_MOBILES"))));
						v2MxOldB.setNotes(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("NOTES"))));
						
						list.add(v2MxOldB);
					}
					scanner.close();
				} catch (Exception e) {
					e.printStackTrace();
				}			
				return list;
			}			
			
			
			 /*public static String switchParam(String name) {  
		          
			      
				 if (name.matches("[a-z]+[A-Z][a-z]+([A-Z][a-z]+)*")){  
			              
			            Pattern pattern = Pattern.compile("[A-Z]");  
			              
			            Matcher matcher = pattern.matcher(name);  
			              
			            while(matcher.find()){  
			                  
			                String old = matcher.group();  
			                String ne = matcher.group().toLowerCase();  
			                  
			                name = name.replaceAll(old, "_"+ne);  
			                  
			            }  
			              
			        }  
			        return name;  

			 }*/
}
