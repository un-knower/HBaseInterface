package com.min.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.hbasedao.V2DbContactDao;
import com.min.model.V2DbContact;
import com.min.model.V2DbMoBase;
import com.min.model.V2DbMxNet;
import com.min.model.V2DbOperatorCall;
import com.min.model.V2DbOperatorTask;
import com.min.model.V2ZScustomerInfo;

@Service
public class V2ServiceImpl implements V2Service {
	@Autowired
	private V2DbContactDao conDao;

	public List<V2DbContact> getContacts(String cid, String addTime) {
		// TODO Auto-generated method stub
		return conDao.getContacts(cid, addTime);
	}

	public List<V2DbMxNet> getMxOldNets(String cid, String addTime) {
		// TODO Auto-generated method stub
		return conDao.getMxOldNets(cid, addTime);
	}

	public V2ZScustomerInfo getCustomr(String idcard, String siteid) {
		// TODO Auto-generated method stub
		return conDao.getCustomr(idcard, siteid);
	}

	public V2DbMoBase getV2DbMoBase(String cid, String addtime) {
		// TODO Auto-generated method stub
		return conDao.getV2DbMoBase(cid, addtime);
	}

	public List<V2DbOperatorCall> getV2DbOperatorCall(String cid, String addtime) {
		// TODO Auto-generated method stub
		return conDao.getV2DbOperatorCall(cid, addtime);}
	public V2DbOperatorTask getOperatorTask(String cid, String addTime) {
		// TODO Auto-generated method stub
		return conDao.getOperatorTask(cid, addTime);
	}

}
