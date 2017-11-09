package com.min.hbasedao.call;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
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

	// 运营商B的通话记录表
	public List<V2DbOperatorCall> getV2DbOperatorCall(String cid, String addtime) {
		// TODO Auto-generated method stub
		List<V2DbOperatorCall> list = new ArrayList<V2DbOperatorCall>();
		try {
			// 根据配置拿到连接
			Connection con = ConnectionFactory.createConnection(conf);
			// 根据连接拿到表
			Table table = con.getTable(TableName.valueOf("V2_DB_OPERATOR_CALL"));
			String column = "call";
			Scan scan = new Scan();
			String rowkey = new StringBuilder(cid).reverse().toString() + "|";
			scan.setRowPrefixFilter(rowkey.getBytes());
			ResultScanner scanner = table.getScanner(scan);
			// 遍历结果
			for (Result res : scanner) {
				long time = Bytes.toLong((res.getValue(Bytes.toBytes(column), Bytes.toBytes("ADDTIME"))));
				if (addtime != null && addtime.length() > 0) {
					long addT = new java.text.SimpleDateFormat("yyyyMM").parse(addtime).getTime() / 1000;
					if (time >= addT && time <= (addT + 3600 * 30 * 24)) {
						V2DbOperatorCall v2DbOpca = new V2DbOperatorCall();
						v2DbOpca.setID(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("ID"))));
						v2DbOpca.setUSERID(
								Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("USERID"))));
						v2DbOpca.setCID(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("CID"))));
						v2DbOpca.setADDTIME(String.valueOf(time));
						v2DbOpca.setPHONEID(
								Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("PHONEID"))));
						v2DbOpca.setTIME(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("TIME"))));
						v2DbOpca.setPEERNUMBER(
								Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("PEERNUMBER"))));
						v2DbOpca.setLOCATION(
								Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("LOCATION"))));
						v2DbOpca.setLOCATIONTYPE(
								Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("LOCATIONTYPE"))));
						v2DbOpca.setDURATIONSEC(
								Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("DURATIONSEC"))));
						v2DbOpca.setDIALTYPE(
								Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("DIALTYPE"))));
						v2DbOpca.setFEE(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("FEE"))));
						v2DbOpca.setCREATETIME(
								Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("CREATIETIME"))));
						v2DbOpca.setLASTMODIFYTIME(
								Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("LASTMODIFYTIME"))));
						v2DbOpca.setCOUNT(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("COUNT"))));
						list.add(v2DbOpca);
					}
				} else {
					V2DbOperatorCall v2DbOpca = new V2DbOperatorCall();
					v2DbOpca.setID(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("ID"))));
					v2DbOpca.setUSERID(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("USERID"))));
					v2DbOpca.setCID(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("CID"))));
					v2DbOpca.setADDTIME(String.valueOf(time));
					v2DbOpca.setPHONEID(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("PHONEID"))));
					v2DbOpca.setTIME(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("TIME"))));
					v2DbOpca.setPEERNUMBER(
							Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("PEERNUMBER"))));
					v2DbOpca.setLOCATION(
							Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("LOCATION"))));
					v2DbOpca.setLOCATIONTYPE(
							Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("LOCATIONTYPE"))));
					v2DbOpca.setDURATIONSEC(
							Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("DURATIONSEC"))));
					v2DbOpca.setDIALTYPE(
							Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("DIALTYPE"))));
					v2DbOpca.setFEE(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("FEE"))));
					v2DbOpca.setCREATETIME(
							Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("CREATIETIME"))));
					v2DbOpca.setLASTMODIFYTIME(
							Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("LASTMODIFYTIME"))));
					v2DbOpca.setCOUNT(Bytes.toString(res.getValue(Bytes.toBytes(column), Bytes.toBytes("COUNT"))));
					list.add(v2DbOpca);
				}
			}
			scanner.close();
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<V2DbContact> getContacts(String cid, String addTime) {
		// TODO Auto-generated method stub
		List<V2DbContact> list = new ArrayList<V2DbContact>();
		try {
			// 根据配置得到连接
			Connection con = ConnectionFactory.createConnection(conf);
			// 根据连接得到表
			Table table = con.getTable(TableName.valueOf("V2_DB_CONTACT"));
			String cloum = "con";
			Scan scan = new Scan();

			// 根据rowkey 前缀过滤查找结果
			scan.setRowPrefixFilter((cid + "|").getBytes());
			ResultScanner scanner = table.getScanner(scan);
			// 遍历结果
			for (Result res : scanner) {
				// System.out.println(res);
				// 保存到实体类
				long time = Bytes.toLong((res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ADDTIME"))));
				if (addTime != null && addTime.length() > 0) {
					long addT = new java.text.SimpleDateFormat("yyyyMM").parse(addTime).getTime() / 1000;
					if (time >= addT && time <= (addT + 3600 * 30 * 24)) {
						V2DbContact v2Con = new V2DbContact();
						v2Con.setId(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ID"))));
						v2Con.setCid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CID"))));
						v2Con.setMobile(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("MOBILE"))));
						v2Con.setAddtime(String.valueOf(time));
						v2Con.setName(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("NAME"))));
						v2Con.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("USERID"))));
						list.add(v2Con);
					}
				} else {
					V2DbContact v2Con = new V2DbContact();
					v2Con.setId(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ID"))));
					v2Con.setCid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("CID"))));
					v2Con.setMobile(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("MOBILE"))));
					v2Con.setAddtime(String.valueOf(time));
					v2Con.setName(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("NAME"))));
					v2Con.setUserid(Bytes.toString(res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("USERID"))));
					list.add(v2Con);
				}
			}
			scanner.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public V2ZScustomerInfo getCustomr(String idcard, String siteid) {
		// TODO Auto-generated method stub
		String rowkey = HbaseUtils.transformRowkey(idcard, siteid);
		if (rowkey == null) {
			return null;
		}
		try {
			V2ZScustomerInfo info = new V2ZScustomerInfo();
			Connection con = ConnectionFactory.createConnection(conf);
			Table table = con.getTable(TableName.valueOf("V2_DB_CUSTOMER_INFO"));
			Result result = table.get(new Get(Bytes.toBytes(rowkey)));
			// 按照需求只需要id
			info.setId(Bytes.toString(result.getValue(Bytes.toBytes("ic"), Bytes.toBytes("ID"))));
			return info;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	// 运营商A的中间表
	public V2DbMoBase getV2DbMoBase(String cid, String addtime) {
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

	public List<V2DbXdBase> getV2DbXdBase(String cid, String addtime) {
		// TODO Auto-generated method stub
		List<V2DbXdBase> list = new ArrayList<V2DbXdBase>();
		try {
			// 根据配置拿到连接
			Connection con = ConnectionFactory.createConnection(conf);
			Table table = con.getTable(TableName.valueOf("V2_DB_XD_BASE"));
			String colum = "xb";// 列族
			Scan scan = new Scan();
			// rowkey设计,反转cid
			System.out.println("cid" + cid);
			String rowkey = new StringBuilder(cid).reverse().toString() + "|";
			// 根据前缀
			scan.setRowPrefixFilter(rowkey.getBytes());
			ResultScanner scanner = table.getScanner(scan);
			// 遍历结果

			for (Result res : scanner) {
				long time = Bytes.toLong((res.getValue(Bytes.toBytes(colum), Bytes.toBytes("ADDTIME"))));
				if (addtime != null && addtime.length() > 0) {
					long addT = new java.text.SimpleDateFormat("yyyyMM").parse(addtime).getTime() / 1000;
					if (time >= addT && time <= (addT + 3600 * 30 * 24)) {
						V2DbXdBase v2XB = new V2DbXdBase();
						v2XB.setID(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("ID")))); // 按照需求只需要ID
						list.add(v2XB);

					}
				} else {
					V2DbXdBase v2XB = new V2DbXdBase();
					v2XB.setID(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("ID"))));
					System.out.println(Bytes.toString(res.getValue(Bytes.toBytes(colum), Bytes.toBytes("ID"))));
					list.add(v2XB);
				}
			}

			scanner.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return list;
	}

	public List<V2DbXdCalls> getV2DbXdCalls(String baseinfo_id, String addtime) {
		// TODO Auto-generated method stub
		List<V2DbXdCalls> list = new ArrayList<V2DbXdCalls>();
		try {
			Connection con = ConnectionFactory.createConnection(conf);
			Table table = con.getTable(TableName.valueOf("V2_DB_XD_CALLS"));
			String colum = "calls";
			Scan scan = new Scan();
			String rowkey = new StringBuilder(baseinfo_id).reverse().toString() + "|";
			scan.setRowPrefixFilter(rowkey.getBytes());
			ResultScanner scanner = table.getScanner(scan);

			for (Result res : scanner) {
				V2DbXdCalls v2DbXdCalls = new V2DbXdCalls();
				long time = Bytes.toLong((res.getValue(Bytes.toBytes(colum), Bytes.toBytes("ADDTIME"))));
				if (addtime != null && addtime.length() > 0) {
					long addT = new java.text.SimpleDateFormat("yyyyMM").parse(addtime).getTime() / 1000;
					if (time >= addT && time <= (addT + 3600 * 30 * 24)) {
						@SuppressWarnings("unchecked")
						Class<V2DbXdCalls> cls = (Class<V2DbXdCalls>) v2DbXdCalls.getClass();
						Field[] fields = cls.getDeclaredFields();
						for (Field field : fields) {
							field.setAccessible(true);
							String fieldName = field.getName();
							field.set(v2DbXdCalls, res.getValue(Bytes.toBytes(colum), // 注意小写转大写
									Bytes.toBytes(fieldName)));
						}
					}
				} else {
					@SuppressWarnings("unchecked")
					Class<V2DbXdCalls> cls = (Class<V2DbXdCalls>) v2DbXdCalls.getClass();
					Field[] fields = cls.getDeclaredFields();
					for (Field field : fields) {
						field.setAccessible(true);
						String fieldName = field.getName();
						System.out.println("kpok0op" + fieldName);
						field.set(v2DbXdCalls, res.getValue(Bytes.toBytes(colum), Bytes.toBytes(fieldName)));
					}
				}
				list.add(v2DbXdCalls);
				scanner.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	public V2DbOperatorTask getOperatorTask(String cid, String addTime) {
		// TODO Auto-generated method stub
		try {
			// 根据配置得到连接
			Connection con = ConnectionFactory.createConnection(conf);
			// 根据连接得到表
			Table table = con.getTable(TableName.valueOf("V2_DB_OPERATOR_TASK"));
			String cloum = "ot"; // 列族

			// 根据表结构的设计注意反转cid
			String rowKey = new StringBuilder(cid).reverse().toString();
			Result res = table.get(new Get(rowKey.getBytes()));
			V2DbOperatorTask v2 = new V2DbOperatorTask();
			// 保存到实体类
			long time = Bytes.toLong((res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ADDTIME"))));
			if (addTime != null && addTime.length() > 0) {
				long addT = new java.text.SimpleDateFormat("yyyyMM").parse(addTime).getTime() / 1000;
				if (time >= addT && time <= (addT + 3600 * 30 * 24)) {
					@SuppressWarnings("unchecked")
					Class<V2DbOperatorTask> cls = (Class<V2DbOperatorTask>) v2.getClass();
					Field[] fields = cls.getDeclaredFields();
					for (Field field : fields) {
						field.setAccessible(true);
						String fieldName = field.getName();
						field.set(v2, res.getValue(Bytes.toBytes(cloum), // 注意小写转大写
								Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase())));
					}
				}
			} else {
				@SuppressWarnings("unchecked")
				Class<V2DbOperatorTask> cls = (Class<V2DbOperatorTask>) v2.getClass();
				Field[] fields = cls.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					field.set(v2, res.getValue(Bytes.toBytes(cloum),
							Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase())));
				}
			}
			return v2;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	public List<V2DbMoRecordsCall> getV2DbMoRecordsCall(String baseInfoId, String addtime) {
		String rowkey = new StringBuilder(baseInfoId).reverse().toString();
		if (rowkey == null) {
			return null;
		}
		List<V2DbMoRecordsCall> list = new ArrayList<V2DbMoRecordsCall>();
		try {
			// 根据配置得到连接
			Connection con = ConnectionFactory.createConnection(conf);
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
				long time = Bytes.toLong((res.getValue(Bytes.toBytes(cloum), Bytes.toBytes("ADDTIME"))));
				if (addtime != null && addtime.length() > 0) {
					long addT = new java.text.SimpleDateFormat("yyyyMM").parse(addtime).getTime() / 1000;
					if (time >= addT && time <= (addT + 3600 * 30 * 24)) {
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
				} else {
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
			}
			scanner.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return list;
	}

	// 测试
	public static void main(String[] args) throws ParseException {
		V2DbCallDaoImpl dao = new V2DbCallDaoImpl();
		int size = dao.getV2DbMoRecordsCall("7117", null).size();
		System.out.println(size);
	}
}
