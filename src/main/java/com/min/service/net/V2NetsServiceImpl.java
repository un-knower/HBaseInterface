package com.min.service.net;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.hbasedao.net.V2DbNetsDao;

@Service
@Transactional(readOnly = true)
public class V2NetsServiceImpl implements V2NetsService {

	@Autowired
	private V2DbNetsDao v2DbNetsDao;

	public Map<String, Object> getV2DbMxOldNets(String baseinfo_id, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return v2DbNetsDao.getV2DbMxOldNets(baseinfo_id,limit,lastRowKey);
	}

	public Map<String, Object> getV2DbXdNets(String baseinfo_id, int limit, String lastRowKey) {
		// TODO Auto-generated method stub
		return v2DbNetsDao.getV2DbXdNets(baseinfo_id,limit,lastRowKey);
	}

}
