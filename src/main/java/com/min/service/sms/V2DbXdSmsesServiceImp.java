package com.min.service.sms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.hbasedao.sms.V2DbXdSmsesDao;
import com.min.model.sms.V2DbXdSmses;

/**
 * 运营商C短信详情Service
 * @author dddd
 * @version 2017-11-11
 */
@Service
@Transactional(readOnly = true)
public class V2DbXdSmsesServiceImp implements V2DbXdSmsesService {

	@Autowired
	private V2DbXdSmsesDao v2DbXdSmsesDao;

	public List<V2DbXdSmses> getV2DbXdSmses(String baseinfo_id) {
		// TODO Auto-generated method stub
		return v2DbXdSmsesDao.getV2DbXdSmses(baseinfo_id);
	}
	
	

}
