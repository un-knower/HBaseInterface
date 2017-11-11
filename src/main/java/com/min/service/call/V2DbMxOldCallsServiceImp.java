/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.service.call;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.hbasedao.call.V2DbMxOldCallsDaoImp;
import com.min.model.call.V2DbMxBase;
import com.min.model.call.V2DbMxOldCalls;

/**
 * 语音详情Service
 * @author dddd
 * @version 2017-11-10
 */
@Service
@Transactional(readOnly = true)
public class V2DbMxOldCallsServiceImp implements V2DbMxOldCallsService{

	@Autowired
	private V2DbMxOldCallsDaoImp v2DbMxOldCallsDaoImp;
	
	public V2DbMxBase getV2DbMxBase(String cid) {
		// TODO Auto-generated method stub
		return v2DbMxOldCallsDaoImp.getV2DbMxBase(cid);
	}

	public List<V2DbMxOldCalls> getV2DbMxOldCalls(String baseinfo_id) {
		// TODO Auto-generated method stub
		return v2DbMxOldCallsDaoImp.getV2DbMxOldCalls(baseinfo_id);
	}

	
}