package com.min.hbasedao.call;

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
import com.min.model.V2DbOperatorTask;
import com.min.model.V2DbXdBase;
import com.min.model.V2ZScustomerInfo;
import com.min.model.call.V2DbContact;
import com.min.model.call.V2DbMoBase;
import com.min.model.call.V2DbMoRecordsCall;
import com.min.model.call.V2DbOperatorCall;
import com.min.model.call.V2DbXdCalls;
import com.min.utils.HbaseUtils;

@Component
public class V2DbCallDaoImpl implements V2DbCallDao {
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

	// 运营商B的通话记录表
	public List<V2DbOperatorCall> getV2DbOperatorCall(String taskid) {
		// TODO Auto-generated method stub
		List<V2DbOperatorCall> list = new ArrayList<V2DbOperatorCall>();
		try {
			// 根据连接拿到表
			Table table = con.getTable(TableName.valueOf("V2_DB_OPERATOR_CALL"));
			String column = "call";
			Scan scan = new Scan();
			String rowkey = new StringBuilder(taskid).reverse().toString() + "|";
			scan.setRowPrefixFilter(rowkey.getBytes());
			ResultScanner scanner = table.getScanner(scan);
			// 遍历结果
			for (Result res : scanner) {
				V2DbOperatorCall v2DbOpca = new V2DbOperatorCall();
				v2DbOpca.setID(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("ID"))));
				v2DbOpca.setUSERID(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("USERID"))));
				v2DbOpca.setCID(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("CID"))));
				v2DbOpca.setPHONEID(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("PHONEID"))));
				v2DbOpca.setTIME(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("TIME"))));
				v2DbOpca.setPEERNUMBER(
						Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("PEERNUMBER"))));
				v2DbOpca.setLOCATION(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("LOCATION"))));
				v2DbOpca.setLOCATIONTYPE(
						Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("LOCATIONTYPE"))));
				v2DbOpca.setDURATIONSEC(
						Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("DURATIONSEC"))));
				v2DbOpca.setDIALTYPE(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("DIALTYPE"))));
				v2DbOpca.setFEE(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("FEE"))));
				v2DbOpca.setCREATETIME(
						Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("CREATIETIME"))));
				v2DbOpca.setLASTMODIFYTIME(
						Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("LASTMODIFYTIME"))));
				v2DbOpca.setCOUNT(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("COUNT"))));
				v2DbOpca.setTASKID(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("TASKID"))));
				list.add(v2DbOpca);
			}
			scanner.close();
			table.close();
		} catch (IOException e) {
			// TODO: handle exception
			return null;
		}
		return list;
	}

	public List<V2DbContact> getContacts(String cid) {
		// TODO Auto-generated method stub
		List<V2DbContact> list = new ArrayList<V2DbContact>();
		try {
			// 根据连接得到表
			Table table = con.getTable(TableName.valueOf("V2_DB_CONTACT"));
			String cloum = "con";
			Scan scan = new Scan();

			// 根据rowkey 前缀过滤查找结果
			scan.setRowPrefixFilter((cid + "|").getBytes());
			ResultScanner scanner = table.getScanner(scan);
			// 遍历结果
			for (Result res : scanner) {
				V2DbContact v2Con = new V2DbContact();
				v2Con.setId(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ID"))));
				v2Con.setCid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CID"))));
				v2Con.setMobile(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("MOBILE"))));
				v2Con.setName(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("NAME"))));
				v2Con.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("USERID"))));
				list.add(v2Con);
			}
			scanner.close();
			table.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return list;
	}

	public V2ZScustomerInfo getCustomr(String idcard, String siteid, String mobile) {
		// TODO Auto-generated method stub
		String rowkey = HbaseUtils.transformRowkey(idcard, siteid, mobile);
		if (rowkey == null) {
			return null;
		}
		try {
			V2ZScustomerInfo info = new V2ZScustomerInfo();
			Table table = con.getTable(TableName.valueOf("V2_DB_CUSTOMER_INFO"));
			Result result = table.get(new Get(Bytes.toBytes(rowkey)));
			// 按照需求只需要id
			info.setId(Bytes.toString(result.getValue(Bytes.toBytes("ic"), Bytes.toBytes("ID"))));
			info.setOperatorType(Bytes.toString(result.getValue(Bytes.toBytes("ic"), Bytes.toBytes("OPERATOR_TYPE"))));
			table.close();
			return info;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	// 运营商A的中间表
	public V2DbMoBase getV2DbMoBase(String cid) {
		// TODO Auto-generated method stub
		String rowkey = new StringBuilder(cid).reverse().toString();
		if (rowkey == null) {
			return null;
		}
		try {
			V2DbMoBase mb = new V2DbMoBase();
			Connection con = ConnectionFactory.createConnection(conf);
			Table table = con.getTable(TableName.valueOf("V2_DB_MO_BASE"));
			Result result = table.get(new Get(Bytes.toBytes(rowkey)));
			mb.setId(Bytes.toString(result.getValue(Bytes.toBytes("mb"), Bytes.toBytes("ID"))));
			return mb;
		} catch (IOException e) {
			return null;
		}
	}
    //运营商B的中间表
	public V2DbOperatorTask getOperatorTask(String cid) {
		// TODO Auto-generated method stub
		try {
			// 根据连接得到表
			Table table = con.getTable(TableName.valueOf("V2_DB_OPERATOR_TASK"));
			String cloum = "ot"; // 列族

			// 根据表结构的设计注意反转cid
			String rowKey = new StringBuilder(cid).reverse().toString();
			Result res = table.get(new Get(rowKey.getBytes()));
			V2DbOperatorTask v2 = new V2DbOperatorTask();
			// 保存到实体类
			v2.setTaskid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), // 注意小写转大写
					Bytes.toBytes("TASKID"))));
			return v2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	//运营商C的中间表
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

	public List<V2DbXdCalls> getV2DbXdCalls(String baseinfo_id) {
		// TODO Auto-generated method stub
		List<V2DbXdCalls> list = new ArrayList<V2DbXdCalls>();
		try {
			Table table = con.getTable(TableName.valueOf("V2_DB_XD_CALLS"));
			String colum = "calls";
			Scan scan = new Scan();
			String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
			scan.setRowPrefixFilter(rowkey.getBytes());
			ResultScanner scanner = table.getScanner(scan);

			for (Result res : scanner) {
				V2DbXdCalls v2DbXdCalls = new V2DbXdCalls();
				@SuppressWarnings("unchecked")
				Class<V2DbXdCalls> cls = (Class<V2DbXdCalls>) v2DbXdCalls.getClass();
				Field[] fields = cls.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					field.set(v2DbXdCalls,
							Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes(fieldName))));
				}
				list.add(v2DbXdCalls);
			}
			scanner.close();
			table.close();
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}

	public List<V2DbMoRecordsCall> getV2DbMoRecordsCall(String baseInfoId) {
		String rowkey = new StringBuilder(baseInfoId).reverse().toString();
		if (rowkey == null) {
			return null;
		}
		List<V2DbMoRecordsCall> list = new ArrayList<V2DbMoRecordsCall>();
		try {
			// 根据连接得到表
			Table table = con.getTable(TableName.valueOf("V2_DB_MO_RECORDS_CALL"));
			String cloum = "call";
			Scan scan = new Scan();

			// 根据rowkey 前缀过滤查找结果
			scan.setRowPrefixFilter((rowkey + "|").getBytes());
			ResultScanner scanner = table.getScanner(scan);
			// 遍历结果
			for (Result res : scanner) {
				// System.out.println(res);
				// 保存到实体类
				V2DbMoRecordsCall v2 = new V2DbMoRecordsCall();
				@SuppressWarnings("unchecked")
				Class<V2DbMoRecordsCall> cls = (Class<V2DbMoRecordsCall>) v2.getClass();
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
			e.printStackTrace();
			return null;
		}
		return list;
	}

	// 测试
	// public static void main(String[] args) throws ParseException {
	// }
}
