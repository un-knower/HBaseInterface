package com.min.service.sms;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.hbasedao.sms.V2DbSmsDao;

@Service
@Transactional(readOnly = true)
public class V2DbSmsServiceImp implements V2DbSmsService {

	@Autowired
	private V2DbSmsDao v2DbSmsDao;

	public Map<String, Object> getV2DbMoRecordsSms(String baseinfo_id, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return v2DbSmsDao.getV2DbMoRecordsSms(baseinfo_id,limit,lastRowKey);
	}

	public Map<String, Object> getV2DbMxOldSmses(String baseinfo_id, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return v2DbSmsDao.getV2DbMxOldSmses(baseinfo_id,limit,lastRowKey);
	}

	public Map<String, Object> getV2DbOperatorSms(String task_id, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return v2DbSmsDao.getV2DbOperatorSms(task_id,limit,lastRowKey);
	}

	public Map<String, Object> getV2DbXdSmses(String baseinfo_id, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return v2DbSmsDao.getV2DbXdSmses(baseinfo_id,limit,lastRowKey);
	}

}
