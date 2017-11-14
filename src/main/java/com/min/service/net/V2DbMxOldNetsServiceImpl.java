package com.min.service.net;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.hbasedao.net.V2DbMxOldNetsDao;
import com.min.model.call.V2DbMxBase;
import com.min.model.net.V2DbMxOldNets;

@Service
@Transactional(readOnly=true)
public class V2DbMxOldNetsServiceImpl implements V2DbMxOldNetsService {
	
	@Autowired
	private V2DbMxOldNetsDao v2DbMxOldNetsDao;

	public List<V2DbMxOldNets> getV2DbMxOldNets(String baseinfo_id) {
		// TODO Auto-generated method stub
		return v2DbMxOldNetsDao.getV2DbMxOldNets(baseinfo_id);
	}
	
	/*
	 * 获取中间表信息
	 */
	public V2DbMxBase getV2DbMxBase(String cid){
		return v2DbMxOldNetsDao.getV2DbMxBase(cid);
	}
}
