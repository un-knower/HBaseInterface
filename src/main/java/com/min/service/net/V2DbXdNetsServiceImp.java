package com.min.service.net;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.min.hbasedao.net.V2DbXdNetsDao;
import com.min.model.V2DbXdBase;
import com.min.model.net.V2DbXdNets;

@Service
@Transactional(readOnly=true)
public class V2DbXdNetsServiceImp implements V2DbXdNetsService {

	@Autowired
	private V2DbXdNetsDao v2DbXdNetsDao;
	
	public List<V2DbXdNets> getV2DbXdNets(String baseinfo_id) {
		// TODO Auto-generated method stub
		return v2DbXdNetsDao.getV2DbXdNets(baseinfo_id);
	}
	
	/*
	 * 获取中间表信息
	 */
	public List<V2DbXdBase> getV2DbXdBase(String cid){
		
		return v2DbXdNetsDao.getV2DbXdBase(cid);
	}

}
