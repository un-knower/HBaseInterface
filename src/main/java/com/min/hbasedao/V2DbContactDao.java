package com.min.hbasedao;

import java.util.List;

import com.min.model.V2DbContact;

public interface V2DbContactDao {
	/*
	 * 根据客户cid获取通讯录列表
	 */
	List<V2DbContact> getContacts(String cid,String addTime);
}
