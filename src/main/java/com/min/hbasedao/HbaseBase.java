package com.min.hbasedao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
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
import org.apache.hadoop.hbase.filter.PageFilter;
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
	private E e;
	private int pageSize = 20; // 每页默认20条
	private int pageIndex = 0; // 第几页
	private byte[] pageStartRowKey = null; // 每页起始rowkey
	private byte[] pageEndRowKey = null; // 每页结束rowkey
	private int queryTotalCount = 0; // 查询总条数
	private List<Result> resultList = new ArrayList<Result>();
	private byte[] preStartRowKey = null;

	/**
	 * 获取分页记录数量
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置分页记录数量
	 * 
	 * @param pageSize
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 获取当前页序号
	 * 
	 * @return
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * 设置当前页序号
	 * 
	 * @param pageIndex
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/**
	 * 获取每页起始行键
	 * 
	 * @return
	 */
	public byte[] getPageStartRowKey() {
		return pageStartRowKey;
	}

	/**
	 * 设置每页起始行键
	 * 
	 * @param pageStartRowKey
	 */
	public void setPageStartRowKey(byte[] pageStartRowKey) {
		this.pageStartRowKey = pageStartRowKey;
	}

	/**
	 * 获取每页结束行键
	 * 
	 * @return
	 */
	public byte[] getPageEndRowKey() {
		return pageEndRowKey;
	}

	/**
	 * 设置每页结束行键
	 * 
	 * @param pageStartRowKey
	 */
	public void setPageEndRowKey(byte[] pageEndRowKey) {
		this.pageEndRowKey = pageEndRowKey;
	}

	/**
	 * 获取已检索总记录数
	 */
	public int getQueryTotalCount() {
		return queryTotalCount;
	}

	/**
	 * 获取已检索总记录数
	 * 
	 * @param queryTotalCount
	 */
	public void setQueryTotalCount(int queryTotalCount) {
		this.queryTotalCount = queryTotalCount;
	}

	/**
	 * 获取HBase检索结果集合
	 * 
	 * @return
	 */
	public List<Result> getResultList() {
		return resultList;
	}

	/**
	 * 设置HBase检索结果集合
	 * 
	 * @param resultList
	 */
	public void setResultList(List<Result> resultList) {
		this.resultList = resultList;
	}

	/**
	 * @Title: getPreStartRowKey
	 * @Description: TODO
	 * @return
	 * @return: byte[]
	 */
	public byte[] getPreStartRowKey() {
		return preStartRowKey;
	}

	/**
	 * @Title: setPreStartRowKey
	 * @Description: 前一页的startrowkey
	 * @param preStartRowKey
	 * @return: void
	 */
	public void setPreStartRowKey(byte[] preStartRowKey) {
		this.preStartRowKey = preStartRowKey;
	}

	/**
	 * @Title:HbaseBase
	 * @Description:构造函数
	 * @param e
	 *            泛型具体的对象
	 * @param pageSize
	 *            每页多少条
	 */
	public HbaseBase(E e, int pageSize) {
		// TODO Auto-generated constructor stub
		this.pageSize = pageSize;
		this.e = e;
	}

	/**
	 * @Title:HbaseBase 构造函数
	 * @param e
	 *            泛型具体的对象
	 */
	public HbaseBase(E e) {
		this.e = e;
	}

	public E getE() {
		return e;
	}

	// 加载配置文件
	static Configuration conf = HBaseConfiguration.create();
	// 根据配置拿到连接
	static Connection con;
	static {
		try {
			// System.out.println("创建连接");
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
			Table table = con.getTable(TableName.valueOf("V2_ZS_CUSTOMER_INFO"));
			Result result = table.get(new Get(Bytes.toBytes(rowkey)));
			V2ZScustomerInfo info = new V2ZScustomerInfo();
			// 按照需求只需要id
			info.setId(Bytes.toString(result.getValue(Bytes.toBytes("c"), Bytes.toBytes("ID"))));
			info.setOperatorType(Bytes.toString(result.getValue(Bytes.toBytes("c"), Bytes.toBytes("OPERATOR_TYPE"))));
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
	 * @Description: 通过rowkey获取hbase表数据(分页)
	 * @param tableName
	 *            hbase表名,不可为空
	 * @param rowkey
	 *            hbase表查询的条件,不可为空
	 * @param columnFamily
	 *            hbase表列族名,不可为空
	 * @param hBase
	 *            分页封装对象
	 * @param lastRowkey
	 * @return
	 * @return: Map<String,Object>
	 */
	public Map<String, Object> scanPage(String tableName, String rowkey, String columnFamily, HbaseBase<E> hBase,
			String lastRowkey) {
		// 对StartRowKey赋值
		if (lastRowkey != null && lastRowkey != "") {
			hBase.setPageStartRowKey(Bytes.toBytes(lastRowkey));
		}
		// 此处的lastRowkey == ""不可省略
		HbaseBase<E> result = hBase.scanResult(tableName,
				(lastRowkey == null || lastRowkey == "" ? null : Bytes.toBytes(lastRowkey)), rowkey, hBase,
				columnFamily);
		List<E> list = new ArrayList<E>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			for (Result res : result.getResultList()) {
				// System.out.println(res);
				// 保存到实体类
				// 将查询结果封装到实体类再放进pageModel的resultList
				E ee = this.getE();
				Class<?> cls = ee.getClass();
				@SuppressWarnings("unchecked")
				E tmp = (E) cls.newInstance();
				// 反射拿到E的属性名
				Field[] fields = cls.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					// 通过列名取到对应的值，在通过反射封装到实体类E里面
					field.set(tmp, Bytes.toString(res.getValue(Bytes.toBytes(columnFamily),
							Bytes.toBytes(HbaseUtils.switchParam(fieldName)))));
				}
				list.add(tmp);
			}
			map.put("msg", "返回成功");
			map.put("code", "200");
			map.put("startrowkey", Bytes.toString(result.getPageStartRowKey()));
			map.put("endrowkey", Bytes.toString(result.getPageEndRowKey()));
			map.put("data", list);
			map.put("prerowkey", result.getPreStartRowKey());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("msg", "没有找到");
			map.put("code", "404");
			map.put("data", "");
		}
		return map;
	}

	/**
	 * @Title: scan
	 * @Description: 通过rowkey获取hbase表数据(不分页)
	 * @param tableName
	 *            hbase表名,不可为空
	 * @param rowkey
	 *            hbase表查询的条件,不可为空
	 * @param columnFamily
	 *            hbase表列族名,不可为空
	 * @param hBase
	 *            分页封装对象
	 * @param lastRowkey
	 * @return
	 * @return: Map<String,Object>
	 */
	public Map<String, Object> scan(String tableName, String rowkey, String columnFamily, HbaseBase<E> hBase) {
		List<E> list = new ArrayList<E>();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Table table = con.getTable(TableName.valueOf(tableName));
			Scan scan = new Scan();
			// 按照前缀查询
			scan.setRowPrefixFilter(Bytes.toBytes(rowkey));
			ResultScanner scanner = table.getScanner(scan);
			for (Result res : scanner) {
				// System.out.println(res);
				// 保存到实体类
				E ee = hBase.getE();
				Class<?> cls = ee.getClass();
				@SuppressWarnings("unchecked")
				E tmp = (E) cls.newInstance();
				// 反射拿到E的属性名
				Field[] fields = cls.getDeclaredFields();
				for (Field field : fields) {
					field.setAccessible(true);
					String fieldName = field.getName();
					// 通过列名取到对应的值，在通过反射封装到实体类E里面
					field.set(tmp, Bytes.toString(res.getValue(Bytes.toBytes(columnFamily),
							Bytes.toBytes(HbaseUtils.switchParam(fieldName)))));
				}
				list.add(tmp);
			}
			map.put("msg", "返回成功");
			map.put("code", "200");
			map.put("data", list);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			map.put("msg", "没有找到");
			map.put("code", "404");
			map.put("data", "");
		}
		return map;
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
			Result res = table.get(new Get(Bytes.toBytes(rowkey)));
			// 保存到实体类
			E ee = this.getE();
			Class<?> cls = ee.getClass();
			Field[] fields = cls.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				String fieldName = field.getName();
				field.set(ee, Bytes.toString(
						res.getValue(Bytes.toBytes(columnFamily), Bytes.toBytes(HbaseUtils.switchParam(fieldName)))));
			}
			table.close();
			return ee;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 分页检索表数据
	 * 
	 * @param tableName
	 *            表名称(*)。
	 * @param startRowKey
	 *            起始行键(可以为空，如果为空，则从表中第一行开始检索)。
	 * @param endRowKey
	 *            结束行键(可以为空)。
	 * @param rowKey
	 *            检索rowKey条件
	 * @param pageModel
	 *            分页模型(*)。
	 * @param columnFamily
	 *            列族名
	 * @return 返回HBasePageModel分页对象。
	 */
	private HbaseBase<E> scanResult(String tableName, byte[] startRowKey, String rowKey, HbaseBase<E> pageModel,
			String columnFamily) {
		// 表名为空返回空
		if (StringUtils.isBlank(tableName)) {
			return pageModel;
		}

		Table table = null;
		try {
			table = con.getTable(TableName.valueOf(tableName));
			int tempPageSize = pageModel.getPageSize();
			boolean isEmptyStartRowKey = false; // 标记是否是第一条开始
			// 如果起始rowkey为空，则从第一条开始
			if (startRowKey == null) {
				// 则读取查询结果的第一行记录
				Result firstResult = selectFirstResultRow(tableName, rowKey);
				// 如果为空则说明没有查到数据
				if (firstResult.isEmpty()) {
					return pageModel;
				}
				// 将第一条结果的rowkey缓存
				startRowKey = firstResult.getRow();
				// 设置前一页的rowkey
				pageModel.setPreStartRowKey(startRowKey);
			}
			// 起始rowkey为空，将startRowKey缓存到pageModel里
			if (pageModel.getPageStartRowKey() == null) {
				isEmptyStartRowKey = true;
				pageModel.setPageStartRowKey(startRowKey);
			} else {
				// 如果结束rowkey不为空，将上一页结束rowkey给当前页的起始rowkey
				if (pageModel.getPageEndRowKey() != null) {
					pageModel.setPageStartRowKey(pageModel.getPageEndRowKey());
				}
				// 从第二页开始，每次都多取一条记录，因为第一条记录是要删除的。
				tempPageSize += 1;
			}

			Scan scan = new Scan();
			// scan.setCaching(100);
			// 按照前缀查询
			scan.setRowPrefixFilter(Bytes.toBytes(rowKey));
			// 设置起始rowkey
			scan.setStartRow(pageModel.getPageStartRowKey());
			// 每次取PageSiz+1条数据
			PageFilter pageFilter = new PageFilter(pageModel.getPageSize() + 1);
			scan.setFilter(pageFilter);

			ResultScanner scanner = table.getScanner(scan);
			List<Result> resultList = new ArrayList<Result>();
			int index = 0; // 标记
			for (Result rs : scanner.next(tempPageSize)) {
				// 如果不是第一页，则跳过结果里的第一条数据
				if (isEmptyStartRowKey == false && index == 0) {
					index += 1;
					continue;
				}
				if (!rs.isEmpty()) {
					resultList.add(rs);
				}
				index += 1;
			}
			// 关闭
			scanner.close();
			pageModel.setResultList(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				table.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int pageIndex = pageModel.getPageIndex() + 1;
		pageModel.setPageIndex(pageIndex);
		if (pageModel.getResultList().size() > 0) {
			// 获取本次分页数据首行和末行的行键信息
			byte[] pageStartRowKey = pageModel.getResultList().get(0).getRow();
			byte[] pageEndRowKey = pageModel.getResultList().get(pageModel.getResultList().size() - 1).getRow();
			pageModel.setPageStartRowKey(pageStartRowKey);
			pageModel.setPageEndRowKey(pageEndRowKey);
		}
		int queryTotalCount = pageModel.getQueryTotalCount() + pageModel.getResultList().size();
		pageModel.setQueryTotalCount(queryTotalCount);

		return pageModel;
	}

	/**
	 * 检索指定表的第一行记录
	 * 
	 * @param tableName
	 *            表名称(*)。
	 * @param rowkey
	 *            前缀查询
	 * @return
	 */
	private static Result selectFirstResultRow(String tableName, String rowkey) {
		if (StringUtils.isBlank(tableName))
			return null;
		Table table = null;
		try {
			// 根据HBase表名称，得到HTable表对象
			table = con.getTable(TableName.valueOf(tableName));
			Scan scan = new Scan();
			scan.setRowPrefixFilter(Bytes.toBytes(rowkey));
			ResultScanner scanner = table.getScanner(scan);
			Iterator<Result> iterator = scanner.iterator();
			int index = 0;
			while (iterator.hasNext()) {
				Result rs = iterator.next();
				if (index == 0) {
					scanner.close();
					return rs;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				table.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
