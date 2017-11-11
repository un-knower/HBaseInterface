package com.min.service.sms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.hbasedao.sms.V2DbOperatorSmsDaoImp;
import com.min.model.sms.V2DbOperatorSms;
import com.min.model.sms.V2DbOperatorTask;

/**
 * 运营商B短信详情Service
 * @author dddd
 * @version 2017-11-10
 */
@Service
@Transactional(readOnly=true)
public class V2DbOperatorSmsServiceImp implements V2DbOperatorSmsService {

	@Autowired
	private V2DbOperatorSmsDaoImp v2DbOperatorSmsDaoImp;
	
	public V2DbOperatorTask getV2DbOperatorTask(String cid) {
		// TODO Auto-generated method stub
		return v2DbOperatorSmsDaoImp.getV2DbOperatorTask(cid);
	}

	public List<V2DbOperatorSms> getV2DbOperatorSms(String task_id) {
		// TODO Auto-generated method stub
		return v2DbOperatorSmsDaoImp.getV2DbOperatorSms(task_id);
	}

}
