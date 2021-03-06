package com.min.service.call;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.min.hbasedao.call.V2DbCallDao;
import com.min.model.V2ZScustomerInfo;
import com.min.model.base.V2DbMoBase;
import com.min.model.base.V2DbMxBase;

@Service
public class V2CallServiceImpl implements V2CallService {
	
	@Autowired
	private V2DbCallDao conDao;
	
	public Map<String, Object> getContacts(String cid, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return conDao.getContacts(cid,limit,lastRowKey);
	}

	public V2ZScustomerInfo getCustomr(String idcard, String siteid, String mobile) {
		// TODO Auto-generated method stub
		return conDao.getCustomr(idcard, siteid, mobile);
	}

	public V2DbMoBase getV2DbMoBase(String cid) {
		// TODO Auto-generated method stub
		return conDao.getV2DbMoBase(cid);
	}

	public Map<String, Object> getV2DbOperatorCall(String cid, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return conDao.getV2DbOperatorCall(cid,limit,lastRowKey);
	}

	public Map<String, Object> getOperatorTask(String cid, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return conDao.getOperatorTask(cid,limit,lastRowKey);
	}

	public Map<String, Object> getV2DbMoRecordsCall(String baseInfoId, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return conDao.getV2DbMoRecordsCall(baseInfoId,limit,lastRowKey);
	}

	public Map<String, Object> getV2DbXdBase(String cid, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return conDao.getV2DbXdBase(cid,limit,lastRowKey);
	}

	public Map<String, Object> getV2DbXdCalls(String baseinfo_id, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return conDao.getV2DbXdCalls(baseinfo_id,limit,lastRowKey);
	}
	
	
	
	public V2DbMxBase getV2DbMxBase(String cid) {
		// TODO Auto-generated method stub
		return conDao.getV2DbMxBase(cid);
	}

	public Map<String, Object> getV2DbMxOldCalls(String baseinfo_id, int limit, String lastRowkey) {
		// TODO Auto-generated method stub
		return conDao.getV2DbMxOldCalls(baseinfo_id,limit,lastRowkey);
	}
}
