package com.min.hbasedao;

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
import com.min.model.V2ZScustomerInfo;
import com.min.utils.HbaseUtils;

/**
 * @ClassName: HbaseBase
 * @Description: hbase查询封装类
 * @author: han
 * @param <E>
 *            实体类类型
 * @date: 2017年11月14日 上午9:21:55
 */
public class HbaseBase<E> {
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

	/**
	 * @Title: getCustomerInfo
	 * @Description: 获取用户信息
	 * @param idcard
	 *            身份证号
	 * @param siteid
	 *            平台id
	 * @param mobile
	 *            手机号
	 * @return
	 * @return: V2ZScustomerInfo
	 */
	public static V2ZScustomerInfo getCustomerInfo(String idcard, String siteid, String mobile) {
		String rowkey = HbaseUtils.transformRowkey(idcard, siteid, mobile);
		if (rowkey == null) {
			return null;
		}
		try {
			Table table = con.getTable(TableName.valueOf("V2_DB_CUSTOMER_INFO"));
			Result result = table.get(new Get(Bytes.toBytes(rowkey)));
			V2ZScustomerInfo info = new V2ZScustomerInfo();
			// 按照需求只需要id
			info.setId(Bytes.toString(result.getValue(Bytes.toBytes("ic"), Bytes.toBytes("ID"))));
			info.setOperatorType(Bytes.toString(result.getValue(Bytes.toBytes("ic"), Bytes.toBytes("OPERATOR_TYPE"))));
			table.close();
			return info;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Title: scan
	 * @Description: 通过rowkey获取hbase表数据
	 * @param tableName
	 *            hbase表名
	 * @param rowkey
	 *            不可为空
	 * @param columnFamily
	 *            列族名
	 * @return
	 * @return: List<E> 实体类集合
	 */
	public List<E> scan(String tableName, String rowkey, String columnFamily) {
		List<E> list = new ArrayList<E>();
		try {
			// 根据连接得到表
			Table table = con.getTable(TableName.valueOf(tableName));
			Scan scan = new Scan();
			// 根据rowkey 前缀过滤查找结果
			scan.setRowPrefixFilter((rowkey).getBytes());
			ResultScanner scanner = table.getScanner(scan);
			// 遍历结果
			for (Result res : scanner) {
				// System.out.println(res);
				// 保存到实体类
				Class<?> cls = this.getClass();
				@SuppressWarnings("unchecked")
				E ee = (E) cls.newInstance();
				Field[] fields = cls.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					field.set(ee, res.getValue(Bytes.toBytes(columnFamily),
							Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase())));
				}
				list.add(ee);
			}
			scanner.close();
			table.close();
			return list;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @Title: get
	 * @Description: 通过具体的rowkey获取hbase表数据
	 * @param tableName
	 *            hbase表名
	 * @param rowkey
	 *            不可为空
	 * @param columnFamily
	 *            列族名
	 * @return
	 * @return: E 实体类
	 */
	public E get(String tableName, String rowkey, String columnFamily) {
		try {
			// 根据连接得到表
			Table table = con.getTable(TableName.valueOf(tableName));
			Result result = table.get(new Get(Bytes.toBytes(rowkey)));
			// 保存到实体类
			Class<?> cls = this.getClass();
			@SuppressWarnings("unchecked")
			E ee = (E) cls.newInstance();
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				field.set(ee, result.getValue(Bytes.toBytes(columnFamily),
						Bytes.toBytes(HbaseUtils.switchParam(fieldName).toUpperCase())));
			}
			table.close();
			return ee;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private Class<E> classType;

	public HbaseBase(Class<E> classType) {
		super();
		this.classType = classType;
	}

	public E getClassType() {
		try {
			return classType.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setClassType(Class<E> classType) {
		this.classType = classType;
	}

	public HbaseBase() {
		super();
	}
}
