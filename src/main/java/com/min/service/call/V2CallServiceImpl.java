package com.min.service.call;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.min.hbasedao.call.V2DbCallDao;
import com.min.model.V2DbOperatorTask;
import com.min.model.V2DbXdBase;
import com.min.model.V2ZScustomerInfo;
import com.min.model.call.V2DbContact;
import com.min.model.call.V2DbMoBase;
import com.min.model.call.V2DbMoRecordsCall;
import com.min.model.call.V2DbOperatorCall;
import com.min.model.call.V2DbXdCalls;

@Service
public class V2CallServiceImpl implements V2CallService {
	@Autowired
	private V2DbCallDao conDao;

	public List<V2DbContact> getContacts(String cid, String addTime) {
		// TODO Auto-generated method stub
		return conDao.getContacts(cid, addTime);
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
		return conDao.getV2DbOperatorCall(cid, addtime);
	}

	public V2DbOperatorTask getOperatorTask(String cid, String addTime) {
		// TODO Auto-generated method stub
		return conDao.getOperatorTask(cid, addTime);
	}

	public List<V2DbMoRecordsCall> getV2DbMoRecordsCall(String baseInfoId, String addtime) {
		// TODO Auto-generated method stub
		return conDao.getV2DbMoRecordsCall(baseInfoId, addtime);
	}

	public List<V2DbXdBase> getV2DbXdBase(String cid, String addtime) {
		// TODO Auto-generated method stub
		return conDao.getV2DbXdBase(cid, addtime);
	}

	public List<V2DbXdCalls> getV2DbXdCalls(String baseinfo_id, String addtime) {
		// TODO Auto-generated method stub
		return conDao.getV2DbXdCalls(baseinfo_id, addtime);
	}
}
