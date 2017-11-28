package com.min.service.net;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.min.hbasedao.net.V2DbMxOldNetsDao;
import com.min.hbasedao.net.V2DbXdNetsDao;

@Service
@Transactional(readOnly = true)
public class V2NetsServiceImpl implements V2NetsService {
	
	@Autowired
	private V2DbXdNetsDao v2DbXdNetsDao;
	
	@Autowired
	private V2DbMxOldNetsDao v2DbMxOldNetsDao;

	public Map<String, Object> getV2DbMxOldNets(String baseinfo_id) {
		// TODO Auto-generated method stub
		return v2DbMxOldNetsDao.getV2DbMxOldNets(baseinfo_id);
	}

	/*
	 * 获取中间表信息
	 */
	public Map<String, Object> getV2DbMxBase(String cid) {
		return v2DbMxOldNetsDao.getV2DbMxBase(cid);
	}

	public Map<String, Object> getV2DbXdNets(String baseinfo_id) {
		// TODO Auto-generated method stub
		return v2DbXdNetsDao.getV2DbXdNets(baseinfo_id);
	}

	/*
	 * 获取中间表信息
	 */
	public Map<String, Object> getV2DbXdBase(String cid) {

		return v2DbXdNetsDao.getV2DbXdBase(cid);
	}

}
