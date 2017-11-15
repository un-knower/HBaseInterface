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
import com.min.model.call.V2DbMxBase;
import com.min.model.sms.V2DbMxOldSmses;

@Component
public class V2DbMxOldSmsesDaoImp implements V2DbMxOldSmsesDao {

	// 运营商的中间表:申请人基本信息表，用来获取该表ID传入V2_DB_MX_OLD_SMSES表中baseinfo_id字段参数
	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();

	// 运营商的中间表:申请人基本信息表，用来获取该表ID传入V2_DB_MX_OLD_SMSES表中baseinfo_id字段参数
	public V2DbMxBase getV2DbMxBase(String cid) {
		try {
			String rowkey = new StringBuilder(cid).reverse().toString();
			if (rowkey == null) {
				return null;
			}

			V2DbMxBase mx = new V2DbMxBase();
			Connection con = ConnectionFactory.createConnection(conf);
			Table table = con.getTable(TableName.valueOf("V2_DB_MX_BASE"));
			Result result = table.get(new Get(Bytes.toBytes(rowkey)));
			mx.setId(Bytes.toString(result.getValue(Bytes.toBytes("m"), Bytes.toBytes("ID"))));
			return mx;
		} catch (IOException e) {
			return null;
		}
	}

	// 获取语音详情
	public List<V2DbMxOldSmses> getV2DbMxOldSmses(String baseinfo_id) {
		if (baseinfo_id == null) {
			return null;
		}
		String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
		HbaseBase<V2DbMxOldSmses> base = new HbaseBase<V2DbMxOldSmses>();
		return base.scan("V2_DB_MX_OLD_SMSES", rowkey, "smses");
	}
}
