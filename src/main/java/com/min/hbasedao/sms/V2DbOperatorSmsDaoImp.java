package com.min.hbasedao.sms;

import java.io.IOException;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import com.min.hbasedao.HbaseBase;
import com.min.model.sms.V2DbOperatorSms;
import com.min.model.sms.V2DbOperatorTask;

@Component
public class V2DbOperatorSmsDaoImp implements V2DbOperatorSmsDao {
	
	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
	
	// 用来获取该表ID传入V2_DB_OPERATOR_SMS表中baseinfo_id字段参数
	public V2DbOperatorTask getV2DbOperatorTask(String cid) {
		try {
			String rowkey = new StringBuilder(cid).reverse().toString();
			if (rowkey == null) {
				return null;
			}

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
	
	/*public V2DbOperatorTask getV2DbOperatorTask(String cid) {
		if (cid == null) {
			return null;
		}
		String rowkey = new StringBuilder(cid).reverse().toString();
		HbaseBase<V2DbOperatorTask> base = new HbaseBase<V2DbOperatorTask>();
		return base.get("V2_DB_OPERATOR_TASK", rowkey, "ot");
	}*/
	
	

	// 获取V2_DB_OPERATOR_SMS表信息
	public List<V2DbOperatorSms> getV2DbOperatorSms(String task_id) {
		if (task_id == null) {
			return null;
		}
		String rowkey = new StringBuilder(task_id).reverse().toString() + "|";
		HbaseBase<V2DbOperatorSms> base = new HbaseBase<V2DbOperatorSms>(new V2DbOperatorSms());
		return base.scan("V2_DB_OPERATOR_SMS", rowkey, "sms");
	}
}
