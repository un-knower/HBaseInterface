package com.min.hbasedao.bill;

import java.io.IOException;
import java.text.ParseException;
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

import com.min.model.V2DbMxOldBills;

@Component
public class V2DbMxOldBillsDaoImp implements V2DbMxOldBillsDao {

			// 加载配置文件
			static Configuration conf = HBaseConfiguration.create();
			// hbase表名
			static String tableName = "V2_DB_MX_OLD_BILLS";

			public List<V2DbMxOldBills> getContacts(String cid, String addTime) {
				List<V2DbMxOldBills> list = new ArrayList<V2DbMxOldBills>();
				try {
					// 根据配置得到连接
					Connection con = ConnectionFactory.createConnection(conf);
					// 根据连接得到表
					Table table = con.getTable(TableName.valueOf(tableName));
					String cloum = "bills";
					Scan scan = new Scan();
					
					// 根据rowkey 前缀过滤查找结果
					scan.setRowPrefixFilter((cid).getBytes());
					ResultScanner scanner = table.getScanner(scan);
					
					// 遍历结果
					for (Result res : scanner) {
						//获取数据库中的时间戳
						String time = Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ADDTIME")));
						//将时间戳转化为long型
						long timelong = Long.parseLong(time);
						if (addTime != null && addTime.length() > 0) {
							//获取当月时间戳
							long addT = new java.text.SimpleDateFormat("yyyyMM").parse(addTime).getTime() / 1000;
							//查看数据库中的时间戳是否在用户填写的参数时间范围内
							if (timelong >= addT && timelong <= (addT + 3600 * 30 * 24)) {
						// 保存到实体类
						V2DbMxOldBills v2MxOldB = new V2DbMxOldBills();
						
						v2MxOldB.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("USERID"))));
						v2MxOldB.setCid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CID"))));
						v2MxOldB.setAddtime(String.valueOf(timelong));
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
						} else {
						
						V2DbMxOldBills v2MxOldB = new V2DbMxOldBills();
						v2MxOldB.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("USERID"))));
						v2MxOldB.setCid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CID"))));
						v2MxOldB.setAddtime(String.valueOf(time));
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
					}
					scanner.close();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return list;
			}			
}
