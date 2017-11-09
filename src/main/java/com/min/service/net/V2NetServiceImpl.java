package com.min.service.net;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.min.hbasedao.net.V2DBNetsDao;
import com.min.model.net.V2DbMxNet;

@Service
public class V2NetServiceImpl {
	@Autowired
	private V2DBNetsDao nDao;

	public List<V2DbMxNet> getMxOldNets(String cid, String addTime) {
		// TODO Auto-generated method stub
		return nDao.getMxOldNets(cid, addTime);
	}
}
