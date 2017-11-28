package com.min.service.sms;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.min.hbasedao.sms.V2DbMoRecordsSmsDao;

/**
 * 运营商C短信详情Service
 * 
 * @author dddd
 * @version 2017-11-11
 */
@Service
@Transactional(readOnly = true)
public class V2DbXdSmsesServiceImp implements V2DbXdSmsesService {

	@Autowired
	private V2DbMoRecordsSmsDao v;

	public Map<String, Object> getV2DbXdSmses(String baseinfo_id) {
		// TODO Auto-generated method stub
		return v.getV2DbXdSmses(baseinfo_id);
	}

}
