/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.min.control.bill;

import java.io.IOException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.min.model.JSON;
import com.min.model.V2DbMxOldBills;
import com.min.model.V2ZScustomerInfo;
import com.min.service.bill.V2DbMxOldBillsService;
import com.min.service.call.V2CallService;
import com.min.utils.HbaseUtils;

/**
 * old_billsController
 * 
 * @author dddd
 * @version 2017-11-03
 */
@Controller
@RequestMapping(value = "/api")
public class V2DbMxOldBillsController {

	@Autowired
	private V2DbMxOldBillsService v2DbMxOldBillsService;

	@Autowired
	private V2CallService service;

	@RequestMapping(value = "/v2/MxOldBills", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	// 账单记录查询
	public void getCusInfoId(HttpServletRequest request, HttpServletResponse response) {
		// 获取addTime
		String addTime = request.getParameter("addtime");
		JSON<V2DbMxOldBills> json = new JSON<V2DbMxOldBills>();
		List<V2DbMxOldBills> contacts = null;

		// 获取cid
		V2ZScustomerInfo customr = service.getCustomr(request.getParameter("idcard"), request.getParameter("siteid"));
		System.out.println("获取cid：" + customr.getId());

		// 获取通讯录
		if (customr.getId() != null) {
			contacts = v2DbMxOldBillsService.getContacts(customr.getId(), addTime);
			json.setCode("200");
			json.setMsg("返回成功");
		} else {
			json.setCode("404");
			json.setMsg("无相关数据");
		}
		json.setData(contacts);
		ObjectMapper mapper = new ObjectMapper();
		try {
			HbaseUtils.setResponse(response);
			String result = mapper.writeValueAsString(json);
			response.getWriter().write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}