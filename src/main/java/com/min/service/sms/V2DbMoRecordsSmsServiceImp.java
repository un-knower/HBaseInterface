package com.min.service.sms;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.min.hbasedao.sms.V2DbMoRecordsSmsDao;
import com.min.model.call.V2DbMoBase;


@Service
@Transactional(readOnly=true)
public class V2DbMoRecordsSmsServiceImp implements V2DbMoRecordsSmsService {

	@Autowired
	private V2DbMoRecordsSmsDao v2DbMoRecordsSmsDao;
	
	public V2DbMoBase getV2DbMoBase(String cid) {
		// TODO Auto-generated method stub
		return v2DbMoRecordsSmsDao.getV2DbMoBase(cid);
	}

	public Map<String, Object> getV2DbMoRecordsSms(String baseinfo_id) {
		// TODO Auto-generated method stub
		return v2DbMoRecordsSmsDao.getV2DbMoRecordsSms(baseinfo_id);
	}

}
