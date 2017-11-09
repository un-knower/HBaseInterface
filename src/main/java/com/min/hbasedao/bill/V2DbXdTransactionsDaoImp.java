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

import com.min.model.V2DbXdTransactions;

@Component
public class V2DbXdTransactionsDaoImp implements V2DbXdTransactionsDao {

	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
	// hbase表名
	static String tableName = "V2_DB_XD_TRANSACTIONS";

	public List<V2DbXdTransactions> getContacts(String cid, String addTime) {
		List<V2DbXdTransactions> list = new ArrayList<V2DbXdTransactions>();

		// 根据配置得到连接
		Connection con;
		try {
			con = ConnectionFactory.createConnection(conf);
			// 根据连接得到表
			Table table = con.getTable(TableName.valueOf(tableName));
			String cloum = "c";
			Scan scan = new Scan();
			// 根据rowkey 前缀过滤查找结果
			scan.setRowPrefixFilter(cid.getBytes());
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
					V2DbXdTransactions v2XdTr = new V2DbXdTransactions();
					
					v2XdTr.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("USERID"))));
					v2XdTr.setCid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CID"))));
					v2XdTr.setAddtime(String.valueOf(timelong));
					v2XdTr.setBaseinfoId(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("BASEINFO_ID"))));
					v2XdTr.setCellPhone(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CELL_PHONE"))));
					v2XdTr.setTotalAmt(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("TOTAL_AMT"))));
					v2XdTr.setUpdateTime(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("UPDATE_TIME"))));
					v2XdTr.setPayAmt(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("PAY_AMT"))));
					v2XdTr.setBillCycle(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("BILL_CYCLE"))));
					v2XdTr.setPlanAmt(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("PLAN_AMT"))));

					list.add(v2XdTr);
					 } 
				} else {
					
					V2DbXdTransactions v2XdTr = new V2DbXdTransactions();
					
					v2XdTr.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("USERID"))));
					v2XdTr.setCid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CID"))));
					v2XdTr.setAddtime(String.valueOf(time));
					v2XdTr.setBaseinfoId(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("BASEINFO_ID"))));
					v2XdTr.setCellPhone(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CELL_PHONE"))));
					v2XdTr.setTotalAmt(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("TOTAL_AMT"))));
					v2XdTr.setUpdateTime(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("UPDATE_TIME"))));
					v2XdTr.setPayAmt(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("PAY_AMT"))));
					v2XdTr.setBillCycle(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("BILL_CYCLE"))));
					v2XdTr.setPlanAmt(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("PLAN_AMT"))));

					list.add(v2XdTr);
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
