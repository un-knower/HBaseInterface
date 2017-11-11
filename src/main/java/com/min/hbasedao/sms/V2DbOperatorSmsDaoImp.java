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
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import com.min.model.call.V2DbMxOldCalls;
import com.min.model.sms.V2DbOperatorSms;
import com.min.model.sms.V2DbOperatorTask;
import com.min.utils.HbaseUtils;

@Component
public class V2DbOperatorSmsDaoImp implements V2DbOperatorSmsDao {

	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
	
	// 用来获取该表ID传入V2_DB_OPERATOR_SMS表中baseinfo_id字段参数
	public V2DbOperatorTask getV2DbOperatorTask(String cid) {
		String rowkey = new StringBuilder(cid).reverse().toString();
		if (rowkey == null) {
			return null;
		}
		try {
			V2DbOperatorTask ot = new V2DbOperatorTask();
			Connection con = ConnectionFactory.createConnection(conf);
			Table table = con.getTable(TableName.valueOf("V2_DB_OPERATOR_TASK"));
			Result result = table.get(new Get(Bytes.toBytes(rowkey)));
			ot.setId(Bytes.toString(result.getValue(Bytes.toBytes("ot"), Bytes.toBytes("ID"))));
			return ot;
		} catch (IOException e) {
			return null;
		}
	}

	//获取V2_DB_OPERATOR_SMS表信息
	public List<V2DbOperatorSms> getV2DbOperatorSms(String task_id) {
		
		List<V2DbOperatorSms> list = new ArrayList<V2DbOperatorSms>();
		try {
			Connection con = ConnectionFactory.createConnection(conf);
			Table table = con.getTable(TableName.valueOf("V2_DB_OPERATOR_SMS"));
			String colum = "sms";
			Scan scan = new Scan();
			
			String rowkey = new StringBuilder(task_id).reverse().toString() + "|";
			scan.setRowPrefixFilter(rowkey.getBytes());
			ResultScanner scanner = table.getScanner(scan);

			for (Result res : scanner) {
				V2DbOperatorSms v2DbOperatorSms = new V2DbOperatorSms();
						@SuppressWarnings("unchecked")
						Class<V2DbOperatorSms> cls = (Class<V2DbOperatorSms>) v2DbOperatorSms.getClass();
						Field[] fields = cls.getDeclaredFields();
						for (Field field : fields) {
							field.setAccessible(true);
							String fieldName = field.getName();
							field.set(v2DbOperatorSms,
									Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase()))));
						}
						list.add(v2DbOperatorSms);
					}
			
			scanner.close();
			table.close();
			
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
