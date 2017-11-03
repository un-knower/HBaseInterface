package com.min.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.dao.V2ZsCustomInfoDao;
import com.min.hbasedao.V2DbContactDao;
import com.min.model.V2DbContact;
import com.min.model.V2ZScustomerInfo;

@Service
public class V2ServiceImpl implements V2Service {
	@Autowired
	private V2ZsCustomInfoDao cusDao;
	@Autowired
	private V2DbContactDao conDao;

	public V2ZScustomerInfo getCustomr(V2ZScustomerInfo info) {
		// TODO Auto-generated method stub
		return cusDao.getCustomr(info);
	}

	public List<V2DbContact> getContacts(String cid) {
		// TODO Auto-generated method stub
		return conDao.getContacts(cid);
	}

}
