package com.min.service.sms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.hbasedao.sms.V2DbMxOldSmsesDao;
import com.min.model.call.V2DbMxBase;
import com.min.model.sms.V2DbMxOldSmses;

/**
 * 运营商D短信详情Service
 * @author dddd
 * @version 2017-11-11
 */
@Service
@Transactional(readOnly = true)
public class V2DbMxOldSmsesServiceImp implements V2DbMxOldSmsesService {

	@Autowired
	private V2DbMxOldSmsesDao v2DbMxOldSmsesDao;
	
	public V2DbMxBase getV2DbMxBase(String cid) {
		// TODO Auto-generated method stub
		return v2DbMxOldSmsesDao.getV2DbMxBase(cid);
	}

	public List<V2DbMxOldSmses> getV2DbMxOldSmses(String baseinfo_id) {
		// TODO Auto-generated method stub
		return v2DbMxOldSmsesDao.getV2DbMxOldSmses(baseinfo_id);
	}

}
